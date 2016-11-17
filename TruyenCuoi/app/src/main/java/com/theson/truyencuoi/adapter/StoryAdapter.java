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
import com.theson.truyencuoi.model.Story;

import java.util.List;


public class StoryAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Story> list;
    //private int imgId;
    private Activity activity;

    public StoryAdapter( List<Story> list) {
            inflater = LayoutInflater.from(App.getContext());
        this.list = list;
        //this.imgId = imgId;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Story getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_story, parent, false);
            holder = new Holder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
           // holder.imgPicture = (ImageView) convertView.findViewById(R.id.img_picture);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if(activity !=null){
            Typeface typeface = Typeface.createFromAsset(activity.getAssets(), "fonts/RobotoLight.ttf");
            Story story = this.list.get(position);
            holder.txtTitle.setTypeface(typeface);
            holder.txtTitle.setText(story.getTitle());
            //holder.imgPicture.setImageResource(imgId);
        }


        return convertView;
    }

    private class Holder {
        TextView txtTitle;
        ///ImageView imgPicture;
    }
}
