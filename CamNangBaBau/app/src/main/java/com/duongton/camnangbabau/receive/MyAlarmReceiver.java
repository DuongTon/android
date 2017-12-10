package com.duongton.camnangbabau.receive;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.activity.LapLichActivity;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.LapLich;
import com.duongton.camnangbabau.model.Vas;


import java.util.Calendar;

/**
 * Created by Theson on 11/30/2017.
 */

public class MyAlarmReceiver extends BroadcastReceiver {
    public static final int REQUEST_COE = 12345;
    private NotificationManager notificationManager;
    private DatabaseManager databaseManager;
    private final int NOTIFICATION_ID = 1010;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("MyAlarmReceiver", "onReceive");
        Intent myIntent = new Intent(context, MyAlarmReceiver.class);
        context.startService(myIntent);

        Calendar calendar = Calendar.getInstance();
        int hour, minute, month, year, day;
        String dayString, hourString;

        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH) + 1;
        year = calendar.get(Calendar.YEAR);

        hour= calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        dayString = day + "/" + month + "/" + year;
        hourString = hour + ":" + convertHour(minute);
        String type = Vas.TYPE_TIEMCHUNG;
        databaseManager = new DatabaseManager(context);
       LapLich lapLich = databaseManager.getDataLapLichNotification(dayString, hourString, type);
       // Log.e("MyAlarmReceiver", arrayList.get(0).getNote() + " ");
        if(lapLich !=null){
            showNotification(context, lapLich.getNote());
            Log.e("MyAlarmReceiver", lapLich.getDate() + "is" + lapLich.getTime() );
        }
        Log.e("MyAlarmReceiver", dayString + "|||" + hourString);
    }
    private static String convertHour(int c) {

        if (c >= 10)
            return String.valueOf(c);

        else

            return "0" + String.valueOf(c);
    }

    private void showNotification(Context context, String title) {
        Intent notificationIntent = new Intent(context, LapLichActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.
                getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] pattern = new long[]{2000, 1000, 2000};
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentIntent(contentIntent)

                //.setTicker("" )
                .setContentTitle("Thông báo Bà Bầu ")
                .setContentText(title)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher))
                .setSmallIcon(R.drawable.ic_launcher)
                .setAutoCancel(true)
                .setSound(defaultSound)
                .setDefaults(Notification.DEFAULT_ALL)
                .setVibrate(pattern);

        Notification notification = new NotificationCompat.BigTextStyle(builder)
                .bigText(title)
                .setBigContentTitle("Thông báo Bà Bầu")
                .build();

            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
