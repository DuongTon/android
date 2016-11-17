package theson.com.ailatrieuphu.activity;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import theson.com.ailatrieuphu.R;


public class LoadGameActivity extends AppCompatActivity {

    private Animation animRotateLogo;
    private ImageView imgLightLogo;

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_game_activity);;
        initializeComponents();
    }

    private void initializeComponents() {
        imgLightLogo = (ImageView) findViewById(R.id.img_light_logo);
        animRotateLogo = AnimationUtils.loadAnimation(this, R.anim.rotate_anim_light_logo);
        imgLightLogo.startAnimation(animRotateLogo);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadGameActivity.this, PlayActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
