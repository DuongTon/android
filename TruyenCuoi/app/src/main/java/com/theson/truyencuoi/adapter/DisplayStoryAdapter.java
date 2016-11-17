package com.theson.truyencuoi.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.theson.truyencuoi.App;
import com.theson.truyencuoi.R;
import com.theson.truyencuoi.activity.DisplayStoryActivity;
import com.theson.truyencuoi.model.Story;

import java.util.List;


public class DisplayStoryAdapter extends PagerAdapter {

    private LayoutInflater inflater;
    private List<Story> list;

    public DisplayStoryAdapter(List<Story> list) {
        inflater = LayoutInflater.from(App.getContext());
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_display, container, false);

        TextView txtDisplay = (TextView) view.findViewById(R.id.txt_display);
        TextView txtTitle = (TextView) view.findViewById(R.id.txt_title_content);
        Story story = list.get(position);
        txtDisplay.setText(story.getContent());
        txtTitle.setText(story.getTitle());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
