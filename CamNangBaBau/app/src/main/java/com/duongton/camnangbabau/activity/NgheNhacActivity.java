package com.duongton.camnangbabau.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.NgheNhacAdapter;
import com.duongton.camnangbabau.model.Song;
import com.duongton.camnangbabau.model.Vas;
import com.duongton.camnangbabau.service.NotificationServiceMusic;

import java.util.ArrayList;

public class NgheNhacActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, NotificationServiceMusic.OnReceiveDataClick, NotificationServiceMusic.OnReceiveDataClickPlay {

    private ImageView btnPrevious;
    private ImageView btnPausePlay;
    private ImageView btnNext;
    private NgheNhacAdapter adapter;
    private ListView listView;
    private ArrayList<Song> arrSong;
    private int position;
    private MediaPlayer mediaPlayer;
    private TextView tvNameMusic;
    private NotificationServiceMusic notificationServiceMusic;
    private boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nghe_nhac);
        initializeComponents();
      //  handleAutoPlay();
        connectionServiceOnBound();

    }

    private void initializeComponents() {
        position = 0;
        btnPausePlay = (ImageView) findViewById(R.id.btn_pause_play);
        btnPrevious = (ImageView) findViewById(R.id.btn_previous);
        btnNext = (ImageView) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        btnPausePlay.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.list_view_music);
        listView.setOnItemClickListener(this);
        initDataMusic();
        tvNameMusic = (TextView) findViewById(R.id.tv_name_song);
        tvNameMusic.setText(arrSong.get(position).getName());
        adapter = new NgheNhacAdapter(arrSong);
        listView.setAdapter(adapter);
        initMediaPlayer(position);
    }

    private void initMediaPlayer(int position){
        mediaPlayer = MediaPlayer.create(NgheNhacActivity.this,arrSong.get(position).getFile());
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

    // b1. Tạo cầu kết nối serviceConnection
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            // là nơi nhận kết quả trả về của service
            NotificationServiceMusic.MyBinderMedia media = (NotificationServiceMusic.MyBinderMedia) iBinder;
            notificationServiceMusic = media.getServiceMusic();

            notificationServiceMusic.setOnReceiveDataClickPlay(NgheNhacActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    // b2. tạo kết nối tới service
    private void connectionServiceOnBound(){
        Intent serviceIntent = new Intent(this, NotificationServiceMusic.class);
        serviceIntent.putExtra("NAME", "Hello Service");
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_next:
                notificationServiceMusic.handlerNext(btnPausePlay,tvNameMusic);
                Toast.makeText(this, "Next", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_previous:
                Intent serviceIntent = new Intent(this, NotificationServiceMusic.class);
                serviceIntent.setAction(Vas.ACTION.PREV_ACTION);
                startService(serviceIntent);
                notificationServiceMusic.handlerPrevious(btnPausePlay, tvNameMusic);
                Toast.makeText(this, "Previous", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_pause_play:
                notificationServiceMusic.setOnReceiveDataClick(this);
                connectionServiceOnStartCommand();
                notificationServiceMusic.handlePausePlay(btnPausePlay);
                break;
            default:
        }
    }

    private void connectionServiceOnStartCommand(){
        Intent serviceIntent = new Intent(this, NotificationServiceMusic.class);
        serviceIntent.putExtra("NAME", position);
        serviceIntent.setAction(Vas.ACTION.STARTFOREGROUND_ACTION);
        startService(serviceIntent);
    }

   /* private void handlePausePlay() {
            if (mediaPlayer.isPlaying()) {
                if(mediaPlayer !=null){
                    mediaPlayer.pause();
                    btnPausePlay.setImageResource(R.drawable.ic_play_blue);
                }
            } else {
                if(mediaPlayer !=null){
                    mediaPlayer.start();
                    btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
                }
            }
            handleAutoPlay();
    }



    private void handlerNext() {
        ++ position;
        if (position > arrSong.size() - 1) {
            position = 0;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(position);
        mediaPlayer.start();
        btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
        tvNameMusic.setText(arrSong.get(position).getName());
        handleAutoPlay();
    }

    private void handlerPrevious() {
        -- position;
        if (position < 0) {
            position = arrSong.size() - 1;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(position);
        mediaPlayer.start();
        btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
        tvNameMusic.setText(arrSong.get(position).getName());
        handleAutoPlay();
    }
*/
    /**
     * Khi hết bài hát tự động play bài hát tiếp theo
     */
  /*  private void handleAutoPlay(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                    }
                    initMediaPlayer(position);
                    mediaPlayer.start();
                    btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
                    handlerNext();
                tvNameMusic.setText(arrSong.get(position).getName());
            }
        });
    }*/

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
        //tvNameMusic.setText(arrSong.get(i).getName());
        connectionServiceOnStartCommand();
        notificationServiceMusic.handlerListViewClick(i,btnPausePlay, tvNameMusic);
        Toast.makeText(this, arrSong.get(i).getName(), Toast.LENGTH_SHORT).show();
    }

    private void handlerListViewClick() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(position);
        mediaPlayer.start();
        btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
    }

    private void startService(){
        Intent intent = new Intent(NgheNhacActivity.this, NotificationServiceMusic.class);
        intent.setAction(Vas.ACTION.STARTFOREGROUND_ACTION);
        intent.putExtra("NAME", position);
        startService(intent);
    }

    @Override
    public void OnDataReceiveData(int position) {
        tvNameMusic.setText(arrSong.get(position).getName());
    }

    @Override
    public void OnDataReceiveData(boolean click) {
        if(click){
            btnPausePlay.setImageResource(R.drawable.ic_play_blue);
        }else {
            btnPausePlay.setImageResource(R.drawable.ic_pause_blue);
        }
    }

    public interface OnReceiveDataDatePickerListener {
        void OnDataReceiveData(boolean click);
    }

}
