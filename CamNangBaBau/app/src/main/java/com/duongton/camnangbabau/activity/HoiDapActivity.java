package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.HoiDapAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.HoiDap;

import java.util.ArrayList;

public class HoiDapActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private HoiDapAdapter adapter;
    private ListView listHoiDap;
    private DatabaseManager databaseManager;
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    private ArrayList<HoiDap> arrayList;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoi_dap);

        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Hỏi đáp");
        listHoiDap = (ListView) findViewById(R.id.list_view_hoidap);
        databaseManager = new DatabaseManager(this);
         arrayList = databaseManager.getDataHoiDap();
        if(arrayList !=null){
            adapter = new HoiDapAdapter(arrayList);
            listHoiDap.setAdapter(adapter);
        }
        listHoiDap.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, HoiDapNoiDungActivity.class);
        intent.putExtra(ID, arrayList.get(i).getId());
        intent.putExtra(NAME, arrayList.get(i).getCauHoi());
        startActivity(intent);
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
