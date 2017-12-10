package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.DocTruyenAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.DocTruyen;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView listView;
    private DocTruyenAdapter adapter;
    private DatabaseManager databaseManager;
    private ArrayList<DocTruyen> arrayList;
    public static final String ID = "ID";
    public static final String MA = "MA";
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        initializeZComponents();
    }

    private void initializeZComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Mẹ kể bé nghe");
        listView = (ListView) findViewById(R.id.listViewDOcTruyen);
        databaseManager = new DatabaseManager(App.getContext());
        arrayList = databaseManager.getDataDocTruyenTenLoai();
        adapter = new DocTruyenAdapter(arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, DocTruyenItemActivity.class);
        intent.putExtra(ID, arrayList.get(i).getMa());
        intent.putExtra(MA, arrayList.get(i).getTen());
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
