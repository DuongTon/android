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
import com.duongton.camnangbabau.adapter.DocTruyenNoiDungAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.DocTruyen;

import java.util.ArrayList;

public class DocTruyenNoiDungActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private DocTruyenNoiDungAdapter adapter;
    private DatabaseManager databaseManager;
    private TextView txtTenTruyen;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen_noi_dung);
        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Mẹ kể bé nghe");
        listView = (ListView) findViewById(R.id.listViewItemNoiDungTruyen);
        txtTenTruyen = (TextView) findViewById(R.id.txtTenTruyen);
        databaseManager = new DatabaseManager(App.getContext());
        Intent intent = getIntent();
        int position;
        String ten;
        if(intent !=null){
            position = intent.getIntExtra(DocTruyenItemActivity.ID,0);
            ten = intent.getStringExtra(DocTruyenItemActivity.MA);
            txtTenTruyen.setText(ten);
            ArrayList<DocTruyen> arrayList = databaseManager.getDataDocTruyenNoiDung(position);
            adapter = new DocTruyenNoiDungAdapter(arrayList);
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
