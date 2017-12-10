package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.DatTen;

import java.util.ArrayList;

/**
 * Created by duong on 10/10/2017.
 */

public class TongQuanAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<DatTen> arrayList;

    public TongQuanAdapter(ArrayList<DatTen> arrayList) {
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
            view = inflater.inflate(R.layout.list_view_tongquan, viewGroup, false);
            holder = new ViewHolder();
            holder.txtNoiDung = view.findViewById(R.id.txtTongQuan);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtNoiDung.setText(arrayList.get(i).getTen());
        return view;
    }

    private class ViewHolder{
        TextView txtNoiDung;
    }
}
