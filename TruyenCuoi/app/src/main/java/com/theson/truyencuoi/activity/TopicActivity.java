package com.theson.truyencuoi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.theson.truyencuoi.R;
import com.theson.truyencuoi.adapter.ToPicAdapter;
import com.theson.truyencuoi.model.Variables;


public class TopicActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvTopic;
    private ToPicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/
        initializeComponents();
    }

    private void initializeComponents() {
        adapter = new ToPicAdapter();
        adapter.setActivity(this);

        lvTopic = (ListView) findViewById(R.id.lv_topics);
        lvTopic.setAdapter(adapter);
        lvTopic.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra(Variables.STORY, position);
        startActivity(intent);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_favorite);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }*/
}
