package theson.com.ailatrieuphu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import theson.com.ailatrieuphu.R;
import theson.com.ailatrieuphu.manager.SoundManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPlayer;
    private ImageView imgLightLogo;
    private Animation animRotateLogo;

    private int btnPress = -1;
    private int destroyBGSound = -2;
    private SoundManager soundManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();

        /*soundManager = new SoundManager(this);
        soundManager.playSoundBG(R.raw.bgmusic);*/
    }

    private void initializeComponents() {
        btnPlayer = (Button) findViewById(R.id.btn_play);
        btnPlayer.setOnClickListener(this);
        imgLightLogo = (ImageView) findViewById(R.id.img_light_logo);
        animRotateLogo = AnimationUtils.loadAnimation(this, R.anim.rotate_anim_light_logo);
        imgLightLogo.startAnimation(animRotateLogo);
    }

    @Override
    public void onClick(View v) {

        if(destroyBGSound == -1){
            soundManager.destroyBG();
            destroyBGSound = 1;
        }
        switch (v.getId()){
            case R.id.btn_play:
                Intent intent = new Intent(this,LoadGameActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(destroyBGSound ==-1){
            soundManager.destroyBG();
            destroyBGSound = 1;
        }
    }

    public void setDestroy(){
        destroyBGSound = -1;
    }
}
