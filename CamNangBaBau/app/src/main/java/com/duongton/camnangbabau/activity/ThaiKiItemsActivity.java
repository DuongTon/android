package com.duongton.camnangbabau.activity;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class ThaiKiItemsActivity extends AppCompatActivity {


    private ListView listView;
    private DatabaseManager databaseManager;
    private ThaiKiItemsAdapter adapter;
    private ImageView imgWeekThaiKi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thai_ki_items);
        initializeComponents();
    }

    private void initializeComponents() {
        listView = (ListView) findViewById(R.id.listViewItemThaiKi);
        imgWeekThaiKi = (ImageView) findViewById(R.id.imgWeekThaiKi);
        databaseManager = new DatabaseManager(this);
        ArrayList<ThaiKi> arrayList = databaseManager.getDataThaiKiItems(ThaiKyActivity.postion + 1);
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                App.getContext().getPackageName() + "/drawable/" + arrayList.get(0).getImage());
        Picasso.with(App.getContext()).load(uri).into(imgWeekThaiKi);
        if(arrayList !=null){
            adapter = new ThaiKiItemsAdapter(arrayList);
            listView.setAdapter(adapter);
        }
    }


}
