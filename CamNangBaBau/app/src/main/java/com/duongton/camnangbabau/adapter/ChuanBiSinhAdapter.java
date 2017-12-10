package com.duongton.camnangbabau.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.CanMuaCanLam;
import com.duongton.camnangbabau.model.ChuanBiSinh;


import java.util.ArrayList;


/**
 * Created by duong on 10/9/2017.
 */

public class ChuanBiSinhAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<Object> arrayList;

    private static final int TYPE_NAME = 0;
    private static final int TYPE_CONTENT = 1;

    public ChuanBiSinhAdapter(ArrayList<Object> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position) instanceof ChuanBiSinh) {
            return TYPE_NAME;
        } else if (arrayList.get(position) instanceof CanMuaCanLam) {
            return TYPE_CONTENT;
        }
        return -1;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        NameHolder nameHolder;
        ContentHolder contentHolder;
        int type = getItemViewType(i);

        if (type == TYPE_NAME) {
            if (view == null) {
                view = inflater.inflate(R.layout.list_view_nguoi_can_chuan_bi, viewGroup, false);
                nameHolder = new NameHolder();
                nameHolder.txtName = view.findViewById(R.id.txtNguoiChuanBiSinh);
                view.setTag(nameHolder);
            } else {
                nameHolder = (NameHolder) view.getTag();
            }
            ChuanBiSinh name = (ChuanBiSinh) arrayList.get(i);
            nameHolder.txtName.setText(name.getName());
            Log.d("CANMUACANLAMCHOME", name.getName());
        } else if (type == TYPE_CONTENT) {
            if (view == null) {
                view = inflater.inflate(R.layout.list_view_chuanbisinh, viewGroup, false);
                contentHolder = new ContentHolder();
                contentHolder.txtContent = view.findViewById(R.id.txtNoiDungChuanBiSinh);
                view.setTag(contentHolder);
            } else {
                contentHolder = (ContentHolder) view.getTag();
            }
            CanMuaCanLam content = (CanMuaCanLam) arrayList.get(i);
            contentHolder.txtContent.setText(content.getNoiDung());
            Log.d("CANMUACANLAM", content.getNoiDung());
        }

        return view;
    }

    private class NameHolder {
        TextView txtName;
    }

    private class ContentHolder {
        TextView txtContent;
    }
}
