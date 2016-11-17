package com.theson.truyencuoi.adapter;


import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.theson.truyencuoi.App;
import com.theson.truyencuoi.R;
import com.theson.truyencuoi.manager.TopicManager;
import com.theson.truyencuoi.model.Topic;

public class ToPicAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ToPicAdapter() {
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return TopicManager.getInstance().getCount();
    }

    @Override
    public Topic getItem(int position) {
        return TopicManager.getInstance().getTopic(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_topics, parent, false);
            holder = new Holder();
            holder.imgTopic = (ImageView) convertView.findViewById(R.id.img_topic);
            holder.txtTopic = (TextView) convertView.findViewById(R.id.txt_topics);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        if (activity != null) {
            Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "fonts/RobotoLight.ttf");
            holder.txtTopic.setTypeface(typeface);
            Topic topic = getItem(position);
            holder.imgTopic.setImageResource(topic.getImageId());
            holder.txtTopic.setText(topic.getTopic());
        }
        return convertView;
    }

    private class Holder {
        ImageView imgTopic;
        TextView txtTopic;
    }
}
