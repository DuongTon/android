package com.duongton.camnangbabau.activity;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.MusicAdapter;
import com.duongton.camnangbabau.model.Song;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class AmNhacActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener {

    private ImageView imgRepeat;
    private ImageView imgPrevious;
    private ImageView imgPausePlay;
    private ImageView imgNext;
    private ImageView imgShuffle;
    private MusicAdapter adapter;
    private ListView listMusic;
    private TextView txtTimeTotal;
    private TextView txtTimeCurrent;
    private ArrayList<Song> arrSong;
    private int position = 0;
    private MediaPlayer mediaPlayer;
    private SeekBar sbTime;
    private Handler handler;
    private boolean isPlay = false;
    public static final int UPDATE_TIME_TEXTVIEW = 3;
    private boolean isShuffle = false;
    private boolean isRepeat = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_nhac);
        initializeComponents();
        initHandler();
    }

    private void initializeComponents() {
        isPlay = true;
        imgRepeat = (ImageView) findViewById(R.id.img_repeat);
        imgPrevious = (ImageView) findViewById(R.id.img_previous);
        imgPausePlay = (ImageView) findViewById(R.id.img_pause_play);
        imgNext = (ImageView) findViewById(R.id.img_next);
        imgShuffle = (ImageView) findViewById(R.id.img_shuffle);
        imgRepeat.setOnClickListener(this);
        imgPrevious.setOnClickListener(this);
        imgPausePlay.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgShuffle.setOnClickListener(this);
        listMusic = (ListView) findViewById(R.id.listViewMusic);
        listMusic.setOnItemClickListener(this);
        txtTimeTotal = (TextView) findViewById(R.id.txt_time_total);
        txtTimeCurrent = (TextView) findViewById(R.id.txt_time_current);
        sbTime = (SeekBar) findViewById(R.id.sb_time_process);
        sbTime.setOnSeekBarChangeListener(this);
        initDataMusic();
        adapter = new MusicAdapter(arrSong);
        listMusic.setAdapter(adapter);
    }

    private void initHandler() {
        initMediaPlayer(position);
        //updateTimeSong();
        updateTimeCurrentSong();
        sbTime.setMax(mediaPlayer.getDuration());
    }


    public void initMediaPlayer(int pos) {
        mediaPlayer = MediaPlayer.create(AmNhacActivity.this, arrSong.get(pos).getFile());
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_repeat:
                checkRepeat();
                break;
            case R.id.img_previous:
                handlerPrevious();
                break;
            case R.id.img_pause_play:
                handlerPausePlay();
                break;
            case R.id.img_next:
                checkNext();
                break;
            case R.id.img_shuffle:
                checkShuffle();
                break;
            default:
        }
    }
    private void checkNext(){
        if (isShuffle) {
            handlerShuffle();
        } else {
            handlerNext();
        }
    }
    private void checkRepeat(){
        if (isRepeat) {
            isRepeat = false;
            imgRepeat.setImageResource(R.drawable.ic_repeat);
            Toast.makeText(this, "Repeat is OFF", Toast.LENGTH_SHORT).show();
        } else {
            isRepeat = true;
            isShuffle = false;
            imgRepeat.setImageResource(R.drawable.ic_repeat_one);
            imgShuffle.setImageResource(R.drawable.ic_shuffle);
            Toast.makeText(this, "Repeat is ON", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkShuffle() {
        if (isShuffle) {
            isShuffle = false;
            Toast.makeText(this, "Shuffle is OFF", Toast.LENGTH_SHORT).show();
            imgShuffle.setImageResource(R.drawable.ic_shuffle);
        } else {
            isShuffle = true;
            isRepeat = false;
            imgRepeat.setImageResource(R.drawable.ic_repeat);
            Toast.makeText(this, "Shuffle is ON", Toast.LENGTH_SHORT).show();
            imgShuffle.setImageResource(R.drawable.ic_shuffle_true);
        }
    }

    private void handlerShuffle() {
        int pos;
        Random rd = new Random();
        int min = 0;
        int max = arrSong.size() - 1; // vi du tu 0 - 9 co 10 so nen max - min + 1
        int range = (max - min);//+ 1;
        pos = rd.nextInt(range);//+ 1;
        Toast.makeText(this, pos + " ", Toast.LENGTH_SHORT).show();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        initMediaPlayer(pos);
        mediaPlayer.start();
        imgPausePlay.setImageResource(R.drawable.ic_pause);
        setTimeTotal();
        //updateTimeSong();
        updateTimeCurrentSong();
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
        imgPausePlay.setImageResource(R.drawable.ic_pause);
        setTimeTotal();
       // updateTimeSong();
        updateTimeCurrentSong();
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
        imgPausePlay.setImageResource(R.drawable.ic_pause);
                /*txtTimeTotal.setText(getTimeFormat(getTimeTotal()));
                sbTime.setMax(mediaPlayer.getDuration());
                updateTimeCurrent();*/
        setTimeTotal();
       // updateTimeSong();
        updateTimeCurrentSong();
    }

    private void handlerPausePlay() {
        if (mediaPlayer.isPlaying()) {
            if(mediaPlayer !=null){
                mediaPlayer.pause();
                imgPausePlay.setImageResource(R.drawable.ic_play);
            }
        } else {
           if(mediaPlayer !=null){
               mediaPlayer.start();
               imgPausePlay.setImageResource(R.drawable.ic_pause);
           }
        }
        //txtTimeTotal.setText(getTimeFormat(getTimeTotal()));
        // updateTimeCurrent();
        setTimeTotal();
        //updateTimeSong();
        updateTimeCurrentSong();
    }

    private void handlerListViewClick() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.start();
        imgPausePlay.setImageResource(R.drawable.ic_pause);
        setTimeTotal();
        //updateTimeSong();
       updateTimeCurrentSong();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        position = i;
        Toast.makeText(this, arrSong.get(i).getName(), Toast.LENGTH_SHORT).show();
        handlerListViewClick();
    }

    private void updateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat time = new SimpleDateFormat("mm:ss");
                txtTimeCurrent.setText(time.format(mediaPlayer.getCurrentPosition()));
                // update sbTime
                sbTime.setProgress(mediaPlayer.getCurrentPosition());
                // kiem tra thoi gian bai hat neu ket thuc chuyen bai
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (isRepeat) {
                           /* position++;
                            if (position > arrSong.size() - 1) {
                                position = 0;
                            }*/
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                            }
                            initMediaPlayer(position);
                            mediaPlayer.start();
                            imgPausePlay.setImageResource(R.drawable.ic_pause);
                            setTimeTotal();
                            updateTimeSong();

                        } else if (isShuffle) {
                            handlerShuffle();
                        } else {
                            handlerNext();
                        }
                    }
                });

                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    private void setTimeTotal() {
        SimpleDateFormat time = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(time.format(mediaPlayer.getDuration()));
        // gan Max cuar sbTime  = mediaplayer.getDuration()
        sbTime.setMax(mediaPlayer.getDuration());
    }
    private void updateTimeCurrentSong(){
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what){
                    case UPDATE_TIME_TEXTVIEW:
                        SimpleDateFormat time = new SimpleDateFormat("mm:ss");
                        txtTimeCurrent.setText(time.format(mediaPlayer.getCurrentPosition()));
                        sbTime.setProgress(mediaPlayer.getCurrentPosition());
                        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                if (isRepeat) {
                                    if (mediaPlayer.isPlaying()) {
                                        mediaPlayer.stop();
                                    }
                                    initMediaPlayer(position);
                                    mediaPlayer.start();
                                    imgPausePlay.setImageResource(R.drawable.ic_pause);
                                    setTimeTotal();
                                    //updateTimeSong();
                                    updateTimeCurrentSong();
                                } else if (isShuffle) {
                                    handlerShuffle();
                                } else {
                                    handlerNext();
                                }
                            }
                        });
                        break;
                }
                return false;
            }
        });
        startThreadTimeCurrent();
    }
    private void startThreadTimeCurrent(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isPlay){
                    Message message = new Message();
                    message.what = UPDATE_TIME_TEXTVIEW;
                    handler.sendMessage(message);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }); thread.start();
    }

    private String getTimeFormat(long time) {
        String tm = "";
        int s;
        int m;
        int h;
        s = (int) (time % 60);
        m = (int) ((time - s) / 60);
        if (m >= 60) {
            h = m / 60;
            m = m % 60;
            if (h > 0) {
                if (h < 10)
                    tm += "0" + h + ":";
                else
                    tm += h + ":";
            }
        }
        if (m < 10)
            tm += "0" + m + ":";
        else
            tm += m + ":";
        if (s < 10)
            tm += "0" + s;
        else
            tm += s + "";
        return tm;
    }


    public int getTimeTotal() {
        return mediaPlayer.getDuration() / 1000; // vi thoi gian la mi li s
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaPlayer.seekTo(sbTime.getProgress());
    }
}
