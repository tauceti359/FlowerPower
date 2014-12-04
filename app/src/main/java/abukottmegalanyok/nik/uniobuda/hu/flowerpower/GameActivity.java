package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.DialogPreference;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.jar.Attributes;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.ClickOccupier;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.Utils;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.VibrateService;


public class GameActivity extends Activity {

    //layout elements
    ImageButton gameLocsolBtn;
    ImageView gameViragImageView;
    ImageButton settingsImageButton;
    RelativeLayout gameBackground;

    //timer and datetime elements
    Calendar c;
    String timerText = "";

    //sensor elements
    SensorManager sensorManager;
    Float acc_y;


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

        //accelereration Sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //findViewById
        gameLocsolBtn = (ImageButton) findViewById(R.id.locsol_btn);
        gameViragImageView = (ImageView) findViewById(R.id.gameimageView);
        //settingsImageButton = (ImageButton) findViewById(R.id.imageButton);

        //status, firstDraw
        flowerStatus = vibrateService.getFlowerStatus();
        gameViragImageView.setImageResource(Utils.getDrawable(getImageResourceName(flowerStatus), "drawable"));

        //onClickListeners
        gameLocsolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //timer for watering
                new CountDownTimer(3000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        //timerText="seconds remaining: " + millisUntilFinished / 1000;
                        timerText=acc_y.toString();
                        //itt kell ellenőrizni a gyorsulásmérő adatait
                        //ha hamarabb visszafordítják a telefont, akkor a locsolás érvénytelen
                        //Toast.makeText(GameActivity.this, timerText, Toast.LENGTH_LONG).show();
                    }

                    public void onFinish() {
                        timerText="done";
                        Toast.makeText(GameActivity.this, timerText, Toast.LENGTH_LONG).show();
                    }
                }.start();



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

//        settingsImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(GameActivity.this, PrefsActivity.class);
//                startActivity(intent);
//
//                /*
//                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(GameActivity.this);
//
//                NumberPickerPreference numberPickerPreference = new NumberPickerPreference(GameActivity.this, null);
//                numberPickerPreference.onCreateDialogView();
//                */
//
//        }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        gameBackground = (RelativeLayout) findViewById(R.id.background);
        SetBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(
                listener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                sensorManager.SENSOR_DELAY_FASTEST
        );

        gameBackground = (RelativeLayout) findViewById(R.id.background);
        SetBackground();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener);
    }

    //set background image dynamically based on system time
    //7-19 daytime
    //19-7 nighttime
    private void SetBackground()
    {
        c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);

        if(hour < 19 && hour > 7)
            gameBackground.setBackgroundResource(R.drawable.nappal);
        else
            gameBackground.setBackgroundResource(R.drawable.ejszaka);
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] values = sensorEvent.values;
            acc_y = values[1];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };


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
