package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.HoiDap;

import java.util.ArrayList;

/**
 * Created by Theson on 11/13/2017.
 */

public class HoiDapAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<HoiDap> arrHoiDap;

    public HoiDapAdapter(ArrayList<HoiDap> arrHoiDap) {
        this.arrHoiDap = arrHoiDap;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrHoiDap.size();
    }

    @Override
    public HoiDap getItem(int i) {
        return arrHoiDap.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_hoidap, viewGroup, false);
            viewHolder.tvCauHoi = view.findViewById(R.id.tv_cauhoi);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvCauHoi.setText(arrHoiDap.get(i).getCauHoi());
        return view;
    }

    private class ViewHolder{
        private TextView tvCauHoi;
    }
}
