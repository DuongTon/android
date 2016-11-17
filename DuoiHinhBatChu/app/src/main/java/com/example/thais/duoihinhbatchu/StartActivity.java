package com.example.thais.duoihinhbatchu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends Activity implements View.OnClickListener{

    private Button btnPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initializeComponents();
    }

    public void initializeComponents(){
        btnPlay = (Button) findViewById(R.id.btn_play);
       btnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btn_play){
            Intent intent = new Intent(this,PlayActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
