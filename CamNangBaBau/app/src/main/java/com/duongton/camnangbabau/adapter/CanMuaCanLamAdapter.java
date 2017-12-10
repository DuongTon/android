package com.duongton.camnangbabau.adapter;

import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.CanMuaCanLam;

import java.util.ArrayList;

/**
 * Created by duong on 10/7/2017.
 */

public class CanMuaCanLamAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<CanMuaCanLam> arrayList;

    public CanMuaCanLamAdapter(ArrayList<CanMuaCanLam> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public CanMuaCanLam getItem(int i) {
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
            view = inflater.inflate(R.layout.list_view_canmua_can_lam, viewGroup, false);
            holder = new ViewHolder();
            holder.txtContent = view.findViewById(R.id.txtContentCanMua);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtContent.setText(arrayList.get(i).getNoiDung());
        return view;
    }

    private class ViewHolder{
        TextView txtContent;
    }
}
