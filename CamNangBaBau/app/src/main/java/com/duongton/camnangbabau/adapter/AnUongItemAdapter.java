package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.AnUong;

import java.util.ArrayList;

/**
 * Created by duong on 10/7/2017.
 */

public class AnUongItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<AnUong> arrayList;

    public AnUongItemAdapter(ArrayList<AnUong> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public AnUong getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.list_view_item_anuong, viewGroup, false);
            holder = new ViewHolder();
            holder.txtContentEat = view.findViewById(R.id.txtContentEat);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txtContentEat.setText(arrayList.get(i).getNoiDung());
        return view;
    }

    private class ViewHolder{
        private TextView txtContentEat;
    }
}
