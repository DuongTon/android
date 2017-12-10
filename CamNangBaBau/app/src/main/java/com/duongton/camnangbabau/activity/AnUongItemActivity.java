package com.duongton.camnangbabau.activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.AnUongItemAdapter;
import com.duongton.camnangbabau.fragment.MonAnFragment;

import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.AnUong;

import java.util.ArrayList;

public class AnUongItemActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private DatabaseManager databaseManager;
    private AnUongItemAdapter adapter;
    private TextView txtNameEst;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_uong_item);
        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        listView = (ListView) findViewById(R.id.listViewItemAnUong);
        txtNameEst = (TextView) findViewById(R.id.txtNameEat);
        databaseManager = new DatabaseManager(App.getContext());
        Intent intent = getIntent();
        int position;
        if(intent !=null){
            position = intent.getIntExtra(MonAnFragment.ID, 0);
            String title = intent.getStringExtra(MonAnFragment.NAME);
            tvTitle.setText(title);
            ArrayList<AnUong> arrayList = databaseManager.getDataAnUongItem(position);
            txtNameEst.setText(arrayList.get(0).getName());
            adapter = new AnUongItemAdapter(arrayList);
            listView.setAdapter(adapter);
        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            default:
        }
    }
}
