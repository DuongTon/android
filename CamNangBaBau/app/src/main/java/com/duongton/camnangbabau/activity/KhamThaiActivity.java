package com.duongton.camnangbabau.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.TiemPhongAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.TiemPhong;

import java.util.ArrayList;

public class KhamThaiActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseManager databaseManager;
    private TiemPhongAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kham_thai);
        initializeComponents();
    }

    private void initializeComponents() {
        listView = (ListView) findViewById(R.id.listViewKhamThai);
        databaseManager = new DatabaseManager(this);
        ArrayList<TiemPhong> arrayList = databaseManager.getDataKhamThai();
        adapter = new TiemPhongAdapter(arrayList);
        listView.setAdapter(adapter);
    }
}
