package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.ClickOccupier;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.Utils;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.VibrateService;


public class GameActivity extends Activity {

    Button gameLocsolBtn;
    ImageView gameViragImageView;

    VibrateService vibrateService;

    int flowerStatus = 0;

    public static final String viragImageREsource = "tr_{szam}";

    public String getImageResourceName(int status){
        String temp = viragImageREsource;
        String temp2 = temp.replace("{szam}", Integer.toString(status));
        return temp2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        vibrateService = new VibrateService();

        gameLocsolBtn = (Button) findViewById(R.id.locsol_btn);
        gameViragImageView = (ImageView) findViewById(R.id.gameimageView);


        flowerStatus = vibrateService.getFlowerStatus();
        vibrateService.init();

        gameViragImageView.setImageResource(Utils.getDrawable(getImageResourceName(flowerStatus), "drawable"));

        gameLocsolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!ClickOccupier.occupy()){
                    Toast.makeText(GameActivity.this, "Koran nyomtad!", Toast.LENGTH_SHORT).show();
                    return;
                }

                vibrateService.vibrate();
                vibrateService.toggle();

                if(flowerStatus == 5){

                   flowerStatus = 0;

                }else{
                    flowerStatus++;
                }
                gameViragImageView.setImageResource(Utils.getDrawable(getImageResourceName(flowerStatus), "drawable"));
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
