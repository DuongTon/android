package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.ThaiKi;

import java.util.ArrayList;

/**
 * Created by duong on 10/6/2017.
 */

public class ThaiKiItemsAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<ThaiKi>  arrayList;

    public ThaiKiItemsAdapter(ArrayList<ThaiKi> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public ThaiKi getItem(int i) {
        return arrayList.get(i);
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
            view = inflater.inflate(R.layout.list_view_thaiki_item, viewGroup, false);
            viewHolder.txtNameItemThaiKi = view.findViewById(R.id.txtNameThaiKiItem);
            viewHolder.imgItemThaiKi = view.findViewById(R.id.imgThaiKiItem);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if(i==0){
            viewHolder.imgItemThaiKi.setBackgroundResource(R.drawable.background_embe);
        }else if(i == 1){
            viewHolder.imgItemThaiKi.setBackgroundResource(R.drawable.background_bame);
        }else viewHolder.imgItemThaiKi.setBackgroundResource(R.drawable.background_meochotuan);

        viewHolder.txtNameItemThaiKi.setText(arrayList.get(i).getName());
        return view;
    }

    private class ViewHolder{
        private TextView txtNameItemThaiKi;
        private ImageView imgItemThaiKi;
    }
}
