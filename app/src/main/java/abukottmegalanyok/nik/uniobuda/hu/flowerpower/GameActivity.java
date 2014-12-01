package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Calendar;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.ClickOccupier;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.Utils;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.VibrateService;


public class GameActivity extends Activity {

    Button gameLocsolBtn;
    ImageView gameViragImageView;
    ImageButton settingsImageButton;
    ImageView gameBackgroundImageView;
    Calendar c;

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

        //vibrateService
        vibrateService = new VibrateService();
        vibrateService.init();

        //findViewById
        gameLocsolBtn = (Button) findViewById(R.id.locsol_btn);
        gameViragImageView = (ImageView) findViewById(R.id.gameimageView);
        settingsImageButton = (ImageButton) findViewById(R.id.imageButton);

        //status, firstDraw
        flowerStatus = vibrateService.getFlowerStatus();
        gameViragImageView.setImageResource(Utils.getDrawable(getImageResourceName(flowerStatus), "drawable"));

        //onClickListeners
        gameLocsolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(!ClickOccupier.occupy()){
                    Toast.makeText(GameActivity.this, "Még nem locsolhatsz!", Toast.LENGTH_SHORT).show();
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

        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        gameBackgroundImageView = (ImageView) findViewById(R.id.gameBackground);

        SetBackground();
    }

    //set background image dynamically based on system time
    //7-19 daytime
    //19-7 nighttime
    private void SetBackground()
    {
        c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        if(hour < 19 && hour > 7)
            gameBackgroundImageView.setImageResource(R.drawable.nappal);
        else
            gameBackgroundImageView.setImageResource(R.drawable.ejszaka);
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
