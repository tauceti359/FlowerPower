package abukottmegalanyok.nik.uniobuda.hu.flowerpower.domain;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import abukottmegalanyok.nik.uniobuda.hu.flowerpower.GameActivity;
import abukottmegalanyok.nik.uniobuda.hu.flowerpower.R;

/**
 * Created by Blero on 2014.11.17..
 */
public class NotificationService {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void createNotification(Context context, int interval) {
       NotificationManager notificationManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                //.setContentTitle("Értesítés minden " + interval + " órában.")
                .setContentTitle("Locsold meg a virágot!")
                .setContentText("Koppintson a locsoláshoz.")
                .setSmallIcon(R.drawable.flowerpower)
                .setOngoing(true)
                .setAutoCancel(false);

             Intent intent = new Intent(context, GameActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(GameActivity.class);
        stackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0 ,intent, PendingIntent.FLAG_UPDATE_CURRENT );

        builder.setContentIntent(pendingIntent);

        notificationManager.notify(5555, builder.build());


    }

    public static void cancelNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancel(5555);
    }


}
