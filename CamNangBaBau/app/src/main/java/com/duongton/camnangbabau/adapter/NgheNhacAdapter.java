package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.Song;

import java.util.ArrayList;

/**
 * Created by Theson on 12/6/2017.
 */

public class NgheNhacAdapter extends BaseAdapter {
    private ArrayList<Song> arrSong;
    private LayoutInflater inflater;

    public NgheNhacAdapter(ArrayList<Song> arrSong) {
        this.arrSong = arrSong;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrSong.size();
    }

    @Override
    public Song getItem(int i) {
        return arrSong.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_music, viewGroup, false);
            holder.tvNameMusic = view.findViewById(R.id.tv_name_music);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvNameMusic.setText(arrSong.get(i).getName());
        return view;
    }
    private class ViewHolder{
        TextView tvNameMusic;
    }
}
