package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.TiemPhong;

import java.util.ArrayList;

/**
 * Created by duong on 10/7/2017.
 */

public class TiemPhongAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<TiemPhong> arrayList;

    public TiemPhongAdapter(ArrayList<TiemPhong> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public TiemPhong getItem(int i) {
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
            view = inflater.inflate(R.layout.list_view_item_tiemphong, viewGroup, false);
            holder = new ViewHolder();
            holder.txtName = view.findViewById(R.id.txtNameTiemPhong);
            holder.txtContent = view.findViewById(R.id.txtContentTiemPhong);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtName.setText(arrayList.get(i).getName());
        holder.txtContent.setText(arrayList.get(i).getContent());
        return view;
    }

    private class ViewHolder{
        TextView txtName;
        TextView txtContent;
    }
}
