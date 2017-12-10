package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.DocTruyenNoiDungAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.DocTruyen;

import java.util.ArrayList;

public class DocTruyenNoiDungActivity extends AppCompatActivity {

    private ListView listView;
    private DocTruyenNoiDungAdapter adapter;
    private DatabaseManager databaseManager;
    private TextView txtTenTruyen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen_noi_dung);
        initializeComponents();
    }

    private void initializeComponents() {
        listView = (ListView) findViewById(R.id.listViewItemNoiDungTruyen);
        txtTenTruyen = (TextView) findViewById(R.id.txtTenTruyen);
        databaseManager = new DatabaseManager(App.getContext());
        Intent intent = getIntent();
        int position =0;
        String ten = null;
        if(intent !=null){
            position = intent.getIntExtra(DocTruyenItemActivity.ID,0);
            ten = intent.getStringExtra(DocTruyenItemActivity.MA);
        }
        txtTenTruyen.setText(ten);
        ArrayList<DocTruyen> arrayList = databaseManager.getDataDocTruyenNoiDung(position);
        adapter = new DocTruyenNoiDungAdapter(arrayList);
        listView.setAdapter(adapter);
    }
}
