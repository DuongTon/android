package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.HoiDapNoiDungAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.HoiDap;

import java.util.ArrayList;

public class HoiDapNoiDungActivity extends AppCompatActivity implements View.OnClickListener {

    private HoiDapNoiDungAdapter adapter;
    private ListView listHoiDap;
    private TextView tvCauHoi;
    private DatabaseManager databaseManager;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoi_dap_noi_dung);
        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Hỏi đáp");
        listHoiDap = (ListView) findViewById(R.id.list_hoidap_noidung);
        tvCauHoi = (TextView) findViewById(R.id.tv_cauhoi);
        databaseManager = new DatabaseManager(this);
        Intent intent = getIntent();
        long id;
        String cauHoi;
        if(intent !=null){
            id =intent.getLongExtra(HoiDapActivity.ID,0);
            cauHoi = intent.getStringExtra(HoiDapActivity.NAME);
            tvCauHoi.setText(cauHoi);
            ArrayList<HoiDap> arrayList = databaseManager.getDataHoiDapNoiDung(id);
            adapter = new HoiDapNoiDungAdapter(arrayList);
            listHoiDap.setAdapter(adapter);
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
