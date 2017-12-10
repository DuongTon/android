package com.duongton.camnangbabau.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.ThaiKiItemsAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.ThaiKi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ThaiKiItemsActivity extends AppCompatActivity implements View.OnClickListener {


    private ListView listView;
    private DatabaseManager databaseManager;
    private ThaiKiItemsAdapter adapter;
    private ImageView imgWeekThaiKi;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thai_ki_items);
        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        listView = (ListView) findViewById(R.id.listViewItemThaiKi);
        imgWeekThaiKi = (ImageView) findViewById(R.id.imgWeekThaiKi);
        databaseManager = new DatabaseManager(this);
        Intent intent = getIntent();
        if(intent !=null){
            int id = intent.getIntExtra(ThaiKyActivity.ID, 0);
            String name = intent.getStringExtra(ThaiKyActivity.NAME);
            tvTitle.setText(name);
            ArrayList<ThaiKi> arrayList = databaseManager.getDataThaiKiItems(id);
            Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    App.getContext().getPackageName() + "/drawable/" + arrayList.get(0).getImage());
            Picasso.with(App.getContext()).load(uri).into(imgWeekThaiKi);
            adapter = new ThaiKiItemsAdapter(arrayList);
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
