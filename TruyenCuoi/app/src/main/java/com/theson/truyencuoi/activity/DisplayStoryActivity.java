package com.theson.truyencuoi.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.theson.truyencuoi.R;
import com.theson.truyencuoi.adapter.DisplayStoryAdapter;
import com.theson.truyencuoi.model.Story;
import com.theson.truyencuoi.model.Variables;

import java.util.ArrayList;


public class DisplayStoryActivity extends AppCompatActivity {

    private ArrayList<Story> arrayList;
    private DisplayStoryAdapter adapter;

    private int topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        initializeComponents();
    }

    private void initializeComponents() {
        arrayList = new ArrayList<>();
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        Bundle bundle = getIntent().getExtras();
        topic = bundle.getInt(Variables.TOPIC);

        int position = bundle.getInt(Variables.POSITION);


        setTitle(Variables.TOPICS[topic]);
        Log.i("topic", topic +"");
        Log.i("postion", position+"");
        for (int i = 0; i<Variables.CONTENT[topic].length ; i++){
            arrayList.add(new Story(Variables.TITLE[topic][i],Variables.CONTENT[topic][i]));
        }

        adapter = new DisplayStoryAdapter(arrayList);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        viewPager.setPageTransformer(true, new RotateUpTransformer());
    }
}
