package com.duongton.camnangbabau.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.TiemPhongAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.TiemPhong;

import java.util.ArrayList;

public class KhamThaiActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private DatabaseManager databaseManager;
    private TiemPhongAdapter adapter;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kham_thai);
        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Khám Thai");
        listView = (ListView) findViewById(R.id.listViewKhamThai);
        databaseManager = new DatabaseManager(this);
        ArrayList<TiemPhong> arrayList = databaseManager.getDataKhamThai();
        adapter = new TiemPhongAdapter(arrayList);
        listView.setAdapter(adapter);
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
