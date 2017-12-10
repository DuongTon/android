package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.ThaiKiAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.ThaiKi;

import java.util.ArrayList;

public class ThaiKyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView listViewThaiKy;
    private DatabaseManager databaseManager;
    private ThaiKiAdapter adapter;
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    private ArrayList<ThaiKi> arrayList;
    private TextView tvTitle;
    private final String TAG = getClass().getSimpleName();
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thai_ky);
        initializeComponents();
        Log.e(TAG, " onCreate");
    }

    private void initializeComponents() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Thai Kỳ");
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        listViewThaiKy = (ListView) findViewById(R.id.listViewThaiKy);
        databaseManager = new DatabaseManager(this);
         arrayList = databaseManager.getDataThaiKi();
        adapter = new ThaiKiAdapter(arrayList);
        listViewThaiKy.setAdapter(adapter);
        listViewThaiKy.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this, ThaiKiItemsActivity.class);
        intent.putExtra(ID, arrayList.get(i).getId());
        intent.putExtra(NAME, arrayList.get(i).getName());
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
