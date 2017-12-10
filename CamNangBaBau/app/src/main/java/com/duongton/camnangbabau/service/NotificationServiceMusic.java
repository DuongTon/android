package com.duongton.camnangbabau.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.activity.NgheNhacActivity;
import com.duongton.camnangbabau.model.Song;
import com.duongton.camnangbabau.model.Vas;

import java.util.ArrayList;

/**
 * Created by Theson on 12/7/2017.
 */

public class NotificationServiceMusic extends Service {

    private ArrayList<Song> arrSong;
    private MediaPlayer mediaPlayer;
    private  int position;
    private boolean isNotification =false;

    private Notification status;
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;
    private RemoteViews views;
    private RemoteViews smallViews;

    private final String TAG = getClass().getSimpleName();

    private OnReceiveDataClick onReceiveDataClick;

    private OnReceiveDataClickPlay onReceiveDataClickPlay;

    public void setOnReceiveDataClick(OnReceiveDataClick onReceiveDataClick) {
        this.onReceiveDataClick = onReceiveDataClick;
    }

    public void setOnReceiveDataClickPlay(OnReceiveDataClickPlay onReceiveDataClickPlay) {
        this.onReceiveDataClickPlay = onReceiveDataClickPlay;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        //. được goi khi su dung bindService
        return new MyBinderMedia();
    }

    @Override
    public void onCreate() {
        position = 0;
        initDataMusic();
        initMediaPlayer(position);
        super.onCreate();
    }



    private void initMediaPlayer(int position){
        mediaPlayer = MediaPlayer.create(this,arrSong.get(position).getFile());
    }

    public void handlePauseAndPlay(){
        if (mediaPlayer.isPlaying()) {
            if(mediaPlayer !=null){
                mediaPlayer.pause();
                views.setImageViewResource(R.id.iv_play, R.drawable.ic_play_blue);
                smallViews.setImageViewResource(R.id.iv_play, R.drawable.ic_play_blue);
                Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
                onReceiveDataClickPlay.OnDataReceiveData(true);
            }
        } else {
            if(mediaPlayer !=null){
                mediaPlayer.start();
                views.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_blue);
                smallViews.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_blue);
                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
                onReceiveDataClickPlay.OnDataReceiveData(false);
            }
        }

        startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
        Intent intent=new Intent();
        intent.setAction(Vas.ACTION.PLAY_ACTION);
        sendBroadcast(intent);
    }

    public void handlePausePlay(ImageView btnPausePlay) {
        if (mediaPlayer.isPlaying()) {
            if(mediaPlayer !=null){
                mediaPlayer.pause();
                btnPausePlay.setImageResource(R.drawable.ic_play_blue);
                Toast.makeText(this, "Play", Toast.LENGTH_SHORT).show();
               /* views.setImageViewResource(R.id.iv_play, R.drawable.ic_play_blue);
                smallViews.setImageViewResource(R.id.iv_play, R.drawable.ic_play_blue);

                startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
                Intent intent=new Intent();
                intent.setAction(Vas.ACTION.STARTFOREGROUND_ACTION);
                sendBroadcast(intent);*/
            }
        } else {
            if(mediaPlayer !=null){
                mediaPlayer.start();
                btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
                Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
               /* views.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_blue);
                smallViews.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_blue);

                startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
                Intent intent=new Intent();
                intent.setAction(Vas.ACTION.STARTFOREGROUND_ACTION);
                sendBroadcast(intent);*/
            }
        }
        /*startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
        Intent intent=new Intent();
        intent.setAction(Vas.ACTION.PLAY_ACTION);
        sendBroadcast(intent);
        handleAutoPlay(btnPausePlay,tvNameMusic);*/
    }

    public void handlerNext() {
        ++ position;
        if (position > arrSong.size() - 1) {
            position = 0;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(position);
        mediaPlayer.start();


        views.setTextViewText(R.id.txt_song_title, arrSong.get(position).getName());
        smallViews.setTextViewText(R.id.txt_song_title, arrSong.get(position).getName());
        startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
        Intent intent=new Intent();
        intent.setAction(Vas.ACTION.NEXT_ACTION);
        sendBroadcast(intent);

        onReceiveDataClick.OnDataReceiveData(position);
    }

    public void handlerNext(ImageView btnPausePlay, TextView tvNameMusic) {
        ++ position;
        if (position > arrSong.size() - 1) {
            position = 0;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(position);

        Log.e(TAG, "NEXT " + position);
        mediaPlayer.start();
        btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
        tvNameMusic.setText(arrSong.get(position).getName());
        handleAutoPlay(btnPausePlay,tvNameMusic);

        views.setTextViewText(R.id.txt_song_title, arrSong.get(position).getName());
        smallViews.setTextViewText(R.id.txt_song_title, arrSong.get(position).getName());
        startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
        Intent intent=new Intent();
        intent.setAction(Vas.ACTION.NEXT_ACTION);
        sendBroadcast(intent);


    }

    public void handlerPrevious(ImageView btnPausePlay, TextView tvNameMusic) {
        -- position;
        if (position < 0) {
            position = arrSong.size() - 1;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(position);

        Log.e(TAG, "PREVIOUS " + position);

        mediaPlayer.start();
        btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
        tvNameMusic.setText(arrSong.get(position).getName());
        handleAutoPlay(btnPausePlay,tvNameMusic);

        views.setTextViewText(R.id.txt_song_title, arrSong.get(position).getName());
        smallViews.setTextViewText(R.id.txt_song_title, arrSong.get(position).getName());
        startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
        Intent intent=new Intent();
        intent.setAction(Vas.ACTION.PREV_ACTION);
        sendBroadcast(intent);
    }

    /**
     * Khi hết bài hát tự động play bài hát tiếp theo
     */
    private void handleAutoPlay(final ImageView btnPausePlay, final TextView tvNameMusic){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                initMediaPlayer(position);
                mediaPlayer.start();
                btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
                handlerNext(btnPausePlay, tvNameMusic);
                tvNameMusic.setText(arrSong.get(position).getName());
            }
        });
    }

    public void handlerListViewClick(int position, ImageView btnPausePlay, TextView tvNameMusic) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(position);
        mediaPlayer.start();
        btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
        tvNameMusic.setText(arrSong.get(position).getName());
        handleAutoPlay(btnPausePlay,tvNameMusic);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getAction().equals(Vas.ACTION.STARTFOREGROUND_ACTION)){
           // position =  intent.getIntExtra("NAME",0);
            if(!isNotification){
                showNotification(arrSong.get(position).getName());
                isNotification = true;
            }
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        }else if(intent.getAction().equals(Vas.ACTION.PREV_ACTION)){
            showNotification(arrSong.get(position).getName());
            Toast.makeText(this, "Clicked Previous", Toast.LENGTH_SHORT).show();
        }else if(intent.getAction().equals(Vas.ACTION.PLAY_ACTION)){
            handlePauseAndPlay();
            Toast.makeText(this, "Clicked Play", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "PLAY ACTION");
        }else if(intent.getAction().equals(Vas.ACTION.NEXT_ACTION)){
            handlerNext();
            Toast.makeText(this, "Click Next", Toast.LENGTH_SHORT).show();
        }else if(intent.getAction().equals(Vas.ACTION.STOPFOREGROUND_ACTION)){
            isNotification = false;
            mediaPlayer.pause();
            Toast.makeText(this, "Received stop Foreground Intent", Toast.LENGTH_SHORT).show();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    public class MyBinderMedia extends Binder{
        public NotificationServiceMusic getServiceMusic(){
            return NotificationServiceMusic.this;
        }
    }

    private void initDataMusic() {
        arrSong = new ArrayList<>();
        arrSong.add(new Song("All Kinds Of Everything", R.raw.allkindsofeverything));
        arrSong.add(new Song("Baby Chopin", R.raw.babychopin));
        arrSong.add(new Song("Brahms Lullaby", R.raw.brahmslullaby));
        arrSong.add(new Song("Daddy Kiss Mama", R.raw.daddykissmama));
        arrSong.add(new Song("Green Leaves", R.raw.greenleaves));
        arrSong.add(new Song("Hearing The Heraid Angeis SIng", R.raw.hearingtheheraidangelssing));
        arrSong.add(new Song("Indian Baby Sleeping", R.raw.indianbabysleeping));
        arrSong.add(new Song("Lullaby", R.raw.lullaby));
        arrSong.add(new Song("Twinkle Twinkle Little Star", R.raw.twinkletwinklelittlestar));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showNotification(String title){
       views = new RemoteViews(getPackageName(),R.layout.custom_nitification);
       smallViews = new RemoteViews(getPackageName(),R.layout.custom_noti_small);

        Intent noIntent = new Intent(this, NgheNhacActivity.class);
        noIntent.setAction(Vas.ACTION.MAIN_ACTION);
        noIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , noIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent previousIntent = new Intent(this, NotificationServiceMusic.class);
        previousIntent.setAction(Vas.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);

        Intent playIntent = new Intent(this, NotificationServiceMusic.class);
        playIntent.setAction(Vas.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        Intent nextIntent = new Intent(this, NotificationServiceMusic.class);
        nextIntent.setAction(Vas.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        Intent closeIntent = new Intent(this, NotificationServiceMusic.class);
        closeIntent.setAction(Vas.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);

        views.setOnClickPendingIntent(R.id.img_privious, ppreviousIntent);
        views.setOnClickPendingIntent(R.id.iv_play, pplayIntent);
        views.setOnClickPendingIntent(R.id.img_next, pnextIntent);
        views.setOnClickPendingIntent(R.id.img_close, pcloseIntent);

        smallViews.setOnClickPendingIntent(R.id.iv_play, pplayIntent);
        smallViews.setOnClickPendingIntent(R.id.iv_next, pnextIntent);
        smallViews.setOnClickPendingIntent(R.id.iv_close, pcloseIntent);



        views.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_blue);
        smallViews.setImageViewResource(R.id.iv_play, R.drawable.ic_pause_blue);

        views.setTextViewText(R.id.txt_song_title, title);
        smallViews.setTextViewText(R.id.txt_song_title, title);


        builder = new NotificationCompat.Builder(this);
        //notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        builder.setSmallIcon(R.drawable.icon);
        builder.setLargeIcon(Vas.getDefaultAlbumArt(this));
        builder.setOngoing(true);
        builder.setAutoCancel(true);
        // khi click vào notification vào activity
        builder.setContentIntent(pendingIntent);
        // tạo ra notification
        status = builder.build();
        //notificationManager.notify(0, status);
        status.contentView = smallViews;

        startForeground(Vas.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
    }

    public interface OnReceiveDataClick {
        void OnDataReceiveData(int position);
    }

    public interface OnReceiveDataClickPlay {
        void OnDataReceiveData(boolean click);
    }

}
