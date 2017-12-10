package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.AnUongItemAdapter;
import com.duongton.camnangbabau.fragment.ThucPhamFragment;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.AnUong;

import java.util.ArrayList;

public class AnUongItemActivity extends AppCompatActivity {

    private ListView listView;
    private DatabaseManager databaseManager;
    private AnUongItemAdapter adapter;
    private TextView txtNameEst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_uong_item);
        initializeComponents();
    }

    private void initializeComponents() {
        listView = (ListView) findViewById(R.id.listViewItemAnUong);
        txtNameEst = (TextView) findViewById(R.id.txtNameEat);

        databaseManager = new DatabaseManager(App.getContext());
        Intent intent = getIntent();
        int position = 0;
        if(intent !=null){
            position = intent.getIntExtra("ID", 0);
        }

        ArrayList<AnUong> arrayList = databaseManager.getDataAnUongItem(position);
        txtNameEst.setText(arrayList.get(0).getName());
        adapter = new AnUongItemAdapter(arrayList);
        listView.setAdapter(adapter);

    }
}
