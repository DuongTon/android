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
 * Created by Theson on 11/14/2017.
 */

public class HoiDapNoiDungAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<HoiDap> arrHoiDap;

    public HoiDapNoiDungAdapter(ArrayList<HoiDap> arrHoiDap) {
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
        ViewHolder holder;
        if(view ==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_view_hoidap_noidung, viewGroup,false);
            holder.tvNoiDung = view.findViewById(R.id.tv_hoidap_noidung);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        holder.tvNoiDung.setText(arrHoiDap.get(i).getNoiDung());
        return view;
    }

    private class ViewHolder{
        TextView tvNoiDung;
    }
}
