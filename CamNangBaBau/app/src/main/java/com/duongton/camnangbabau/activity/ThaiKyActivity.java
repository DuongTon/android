package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.ThaiKiAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.ThaiKi;

import java.util.ArrayList;

public class ThaiKyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listViewThaiKy;
    private DatabaseManager databaseManager;
    private ThaiKiAdapter adapter;
    public static int postion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thai_ky);
        initializeComponents();
    }

    private void initializeComponents() {
        listViewThaiKy = (ListView) findViewById(R.id.listViewThaiKy);
        databaseManager = new DatabaseManager(this);
        ArrayList<ThaiKi> arrayList = databaseManager.getDataThaiKi();
        adapter = new ThaiKiAdapter(arrayList);
        listViewThaiKy.setAdapter(adapter);
        listViewThaiKy.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ThaiKiItemsActivity.class);
        postion = i;
        startActivity(intent);
    }
}
