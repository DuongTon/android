package com.duongton.camnangbabau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongton.camnangbabau.R;

/**
 * Created by duong on 10/4/2017.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private String[] nameMenu;
    private int []imageMenu;

    public GridViewAdapter(Context context, String[] nameMenu, int[] imageMenu) {
        this.context = context;
        this.nameMenu = nameMenu;
        this.imageMenu = imageMenu;
    }

    @Override
    public int getCount() {
        return nameMenu.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.grid_view_row,null);
        TextView txtMenu = view.findViewById(R.id.txtMenu);
        ImageView imageView = view.findViewById(R.id.imgMenu);
        txtMenu.setText(nameMenu[i]);
        imageView.setImageResource(imageMenu[i]);
        return view;
    }
}
