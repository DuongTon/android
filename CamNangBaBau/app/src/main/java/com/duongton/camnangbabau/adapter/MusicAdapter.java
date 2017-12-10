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
 * Created by duong on 10/23/2017.
 */

public class MusicAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Song> arrSong;

    public MusicAdapter(ArrayList<Song> arrSong) {
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
        Holder holder;
        if(view == null){
            holder = new Holder();
            view = inflater.inflate(R.layout.list_view_item_music, viewGroup, false);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtStt = view.findViewById(R.id.txtStt);
            view.setTag(holder);
        }else{
            holder = (Holder) view.getTag();
        }
        holder.txtName.setText(arrSong.get(i).getName());
        holder.txtStt.setText(i + 1 + "");
        return view;
    }

    private class Holder{
        private TextView txtName;
        private TextView txtStt;
    }
}
