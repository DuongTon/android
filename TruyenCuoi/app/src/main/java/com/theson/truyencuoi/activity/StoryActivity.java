package com.theson.truyencuoi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.theson.truyencuoi.R;
import com.theson.truyencuoi.adapter.StoryAdapter;
import com.theson.truyencuoi.model.Story;
import com.theson.truyencuoi.model.Variables;

import java.util.ArrayList;

public class StoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvStory;
    private StoryAdapter adapter;
    private ArrayList<Story> arrStory;
    private int topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        // lấy ActionBar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        initializeComponents();


    }

    private void initializeComponents() {

        Bundle bundle = getIntent().getExtras();
        topic = bundle.getInt(Variables.STORY);
        Log.i("test",topic+"");
        setTitle(Variables.TOPICS[topic]);

        arrStory = new ArrayList<>();

        lvStory = (ListView) findViewById(R.id.lv_story);

        for(int i =0; i<Variables.CONTENT[topic].length;i++){
            arrStory.add(new Story(Variables.TITLE[topic][i], Variables.CONTENT[topic][i]));
        }
        adapter = new StoryAdapter(arrStory);
        adapter.setActivity(this);

        lvStory.setAdapter(adapter);

        lvStory.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(StoryActivity.this,DisplayStoryActivity.class);
        intent.putExtra(Variables.POSITION, position);
        intent.putExtra(Variables.TOPIC,topic);
        startActivity(intent);
    }
}
