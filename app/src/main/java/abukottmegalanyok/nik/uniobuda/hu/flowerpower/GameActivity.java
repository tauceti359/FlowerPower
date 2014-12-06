package abukottmegalanyok.nik.uniobuda.hu.flowerpower;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.DialogPreference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.lang.Math;
import java.util.jar.Attributes;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.ClickOccupier;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.Flower;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.FlowerFragment;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.Utils;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain.VibrateService;


public class GameActivity extends Activity {

    //layout elements
    ImageButton gameLocsolBtn;
    ImageView gameViragImageView;
    ImageButton settingsImageButton;
    RelativeLayout gameBackground;
//    Flower flower;
    FlowerFragment flowerFragment;

    //timer and datetime elements
    Calendar c; //for backgroundchange
    boolean wateringOk; //for dataflow between timers
    CountDownTimer wateringTimer;
    CountDownTimer betweenWatering;
    CountDownTimer afterWateringWarning;


    //sensor elements
    SensorManager sensorManager;
    Float acc_x;

    VibrateService vibrateService;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    int flowerLevel;

    //public static final String viragImageREsource = "tr_{szam}";

//    public String getImageResourceName(int status){
//        String temp = viragImageREsource;
//        String temp2 = temp.replace("{szam}", Integer.toString(status));
//        return temp2;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        flowerFragment = new FlowerFragment();
        fragmentTransaction.replace(android.R.id.content, flowerFragment);

        fragmentTransaction.commit();

        //vibrateService
        vibrateService = new VibrateService();
        vibrateService.init();

        //accelereration Sensor
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //findViewById
        gameLocsolBtn = (ImageButton) findViewById(R.id.locsol_btn);
//        flower = (Flower) findViewById(R.id.flower);

        //gameViragImageView = (ImageView) findViewById(R.id.gameimageView);

        settingsImageButton = (ImageButton) findViewById(R.id.imageButton);

        //status, firstDraw
        //flowerStatus = vibrateService.getFlowerStatus();
        //gameViragImageView.setImageResource(Utils.getDrawable(getImageResourceName(flowerStatus), "drawable"));

        //onClickListeners

        gameLocsolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameLocsolBtn.setEnabled(false);
                gameLocsolBtn.setImageResource(R.drawable.wateringcan_disabled);
                wateringTimer.start();
//                if(!ClickOccupier.occupy()){
//                    Toast.makeText(GameActivity.this, "Még nem locsolhatsz!", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                vibrateService.vibrate();
//                vibrateService.toggle();
//
//                if(flowerStatus == 5){
//
//                   flowerStatus = 0;
//
//                }else{
//                    flowerStatus++;
//                }
                //gameViragImageView.setImageResource(Utils.getDrawable(getImageResourceName(flowerStatus), "drawable"));           fl
            }

        });

        //this is a 3sec timer for watering
        wateringTimer = new CountDownTimer(2000, 1000) {
            Float watering_max = -20f;
            Float watering_min = 20f;
            String timerText = "";

            public void onTick(long millisUntilFinished) {
                timerText= acc_x.toString();
                float temp = Math.abs(acc_x);

                if(temp > watering_max)
                    watering_max = temp;

                if(temp < watering_min)
                    watering_min = temp;
            }

            public void onFinish() {
                if(watering_max < 10.5f && watering_min > 9.5f){

//                    flower.LevelUp();
                    flowerFragment.SetFlowerLevelUp();
                    Log.i("levelup", "levelup");
//                    flower.Refresh();
                    flowerFragment.FragmentRefresh();

                    Fragment old = flowerFragment;
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.detach(flowerFragment);
                    fragmentTransaction.attach(old);
                    fragmentTransaction.commit();
                    betweenWatering.start();
                    wateringOk = true;
                    timerText="A locsolás sikeres Level: " + Integer.toString(flowerFragment.GetFlowerLevel());
                    //TODO a virág képét kellene állítani az élénkebb zöldre
                }
                else {
                    //timerText = "max: " + watering_max.toString() + ", min: " + watering_min.toString();
                    timerText = "A locsolás sikertelen, próbáld újra";
                    wateringOk = false;
                    gameLocsolBtn.setEnabled(true);
                    gameLocsolBtn.setImageResource(R.drawable.wateringcan);
                    afterWateringWarning.start();
                }
                watering_max = -20f;
                watering_min = 20f;

                Toast.makeText(GameActivity.this, timerText, Toast.LENGTH_LONG).show();
            }
        };

        //timer to measure the time between two watering (now 5sec)
        //start after successfull watering
        //the watering button is disabled in this period
        //TODO ezt kellene állítani settingsből
        betweenWatering = new CountDownTimer(8000,1000) {
            String timerText = "";

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                timerText = "A virágot meg kell locsolni!!! Level: " + Integer.toString(flowerFragment.GetFlowerLevel());
                wateringOk = false;
                gameLocsolBtn.setEnabled(true);
                gameLocsolBtn.setImageResource(R.drawable.wateringcan);
                vibrateService.start();
                vibrateService.vibrate();
                afterWateringWarning.start();

                //TODO a virág képét kellene állítani a halványabb zöldre
                Toast.makeText(GameActivity.this, timerText, Toast.LENGTH_LONG).show();
            }
        };

        //start after timeout betweenWatering
        afterWateringWarning = new CountDownTimer(10000, 1000) {
            String timerText = "";

            public void onTick(long l) {
            }

            public void onFinish() {
                if(!wateringOk){
                    if(flowerFragment.GetFlowerLevel() != 0) {
//                        flower.LevelDown();
                        flowerFragment.SetFlowerLevelDown();
                        Log.i("leveldown", "leveldown");
//                        flower.Refresh();
                        flowerFragment.FragmentRefresh();
                        timerText = "A virágod szintje csökken, mert nem locsoltad meg Level: " + Integer.toString(flowerFragment.GetFlowerLevel()) ;
                        //this.start();
                        betweenWatering.start();
                    }
                    else
                        timerText = "A virágod visszajutott a kezdeti állapotba Level: " + Integer.toString(flowerFragment.GetFlowerLevel());

                    Toast.makeText(GameActivity.this, timerText, Toast.LENGTH_LONG).show();
                }
                else {
                    this.cancel(); //timer is canceled if the watering button is clicked
                    vibrateService.stop();
                }
            }
        };



        settingsImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(GameActivity.this, PrefsActivity.class);
                startActivity(intent);

                /*
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(GameActivity.this);

                NumberPickerPreference numberPickerPreference = new NumberPickerPreference(GameActivity.this, null);
                numberPickerPreference.onCreateDialogView();
                */

        }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i("levelstart", "levelstart");
        gameBackground = (RelativeLayout) findViewById(R.id.background);
        SetBackground();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //register listener to accelerometer
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

    @Override
    protected void onStop() {
        super.onStop();
        vibrateService.stop();
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
            acc_x = values[0]; //it's enough only x acclereration
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

    public int getActualListPrefValue(){

        SharedPreferences defaultPrefs;
        defaultPrefs = PreferenceManager.getDefaultSharedPreferences(FlowerPowerApplication.getAppContext());


        return Integer.parseInt(defaultPrefs.getString("listpref", "1"));
    }
}
