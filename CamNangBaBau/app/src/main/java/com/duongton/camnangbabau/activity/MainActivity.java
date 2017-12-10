package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.fragment.FragmentMain;
import com.duongton.camnangbabau.fragment.FragmentMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                Intent intent = new Intent(this, DanhMucActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
