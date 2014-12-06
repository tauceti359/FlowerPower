package abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Date;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.FlowerPowerApplication;

/**
 * Created by Blero on 2014.11.17..
 */
public class VibrateService {
    private static final int MULTIPLIER = 60 * 1000;


    protected NotificationService notifcationService;

    protected SharedPreferences prefs;
    protected boolean isStarted;

    public VibrateService() {
        notifcationService = new NotificationService();
        prefs = PreferenceManager.getDefaultSharedPreferences(FlowerPowerApplication.getAppContext());
    }

    public void vibrate() {
        Context context = FlowerPowerApplication.getAppContext();
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
    }

    public boolean init() {
        isStarted = isMyServiceRunning();
        notifcationService.cancelNotification(FlowerPowerApplication.getAppContext());
//        if (isStarted) {
//            notifcationService.createNotification(FlowerPowerApplication.getAppContext(), getInterval());
//        }

        return isStarted;
    }

    public boolean toggle() {
        if (isStarted) {
            stop();
            notifcationService.cancelNotification(FlowerPowerApplication.getAppContext());
        } else {
            start();
        }

        return isStarted;
    }

    public void intervalChanged() {
        if (isStarted) {
            stop();
            start();
        }
    }

    public void start() {
        startWithAlarmManager();
        isStarted = true;
        notifcationService.createNotification(FlowerPowerApplication.getAppContext(), getInterval());
    }

    public void stop() {
        isStarted = false;
        notifcationService.cancelNotification(FlowerPowerApplication.getAppContext());
        Intent intent = new Intent(FlowerPowerApplication.getAppContext(), VibrateReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(FlowerPowerApplication.getAppContext(), 521, intent, 0);
        AlarmManager alarmManager = (AlarmManager) FlowerPowerApplication.getAppContext().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

    }

    private void startWithAlarmManager() {
        Date when = new Date(System.currentTimeMillis());

        try {
            Intent someIntent = new Intent(FlowerPowerApplication.getAppContext(), VibrateReceiver.class); // intent to be launched

            // note this could be getActivity if you want to launch an activity
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    FlowerPowerApplication.getAppContext(),
                    521, // id, optional
                    someIntent, // intent to launch
                    PendingIntent.FLAG_CANCEL_CURRENT); // PendintIntent flag

            AlarmManager alarms = (AlarmManager) FlowerPowerApplication.getAppContext().getSystemService(
                    Context.ALARM_SERVICE);

            alarms.setRepeating(AlarmManager.RTC_WAKEUP,
                    when.getTime(),
                    getInterval() * MULTIPLIER,
                    pendingIntent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getFlowerStatus(){
        return prefs.getInt("flowerStatus", 0);
    }

    public void setFlowerStatus(int status){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("flowerStatus", status);
        editor.commit();
    }

    private int getInterval() {
        return prefs.getInt("interval", 1);
    }

    private boolean isMyServiceRunning() {

        Intent intent = new Intent(FlowerPowerApplication.getAppContext(), VibrateReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(FlowerPowerApplication.getAppContext(), 521, intent, PendingIntent.FLAG_NO_CREATE);

        if (pendingIntent != null) {
            Log.i("", "nem null");
            return true;
        }
        Log.i("", "null");
        return false;
    }

}
