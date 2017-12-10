package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.DatTen;

import java.util.ArrayList;

/**
 * Created by duong on 10/10/2017.
 */

public class DatTenAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<DatTen> arrayList;

    public DatTenAdapter(ArrayList<DatTen> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public DatTen getItem(int i) {
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
            view = inflater.inflate(R.layout.list_view_datten, viewGroup, false);
            holder = new ViewHolder();
            holder.txtTen = view.findViewById(R.id.txtNameDatTen);
            holder.txtYNghia = view.findViewById(R.id.txtYNghiaTen);
            holder.txtTenKhac = view.findViewById(R.id.txtContentDatTen);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        DatTen datTen = arrayList.get(i);
        String ten = datTen.getId() + ". " + datTen.getTen() + ": ";
        holder.txtTen.setText(ten);
        holder.txtYNghia.setText(datTen.getyNghia());
        holder.txtTenKhac.setText(datTen.getTenKhac());
        return view;
    }

    private class ViewHolder{
        private TextView txtTen;
        private TextView txtYNghia;
        private TextView txtTenKhac;
    }
}
