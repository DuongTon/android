package com.duongton.camnangbabau.adapter;

import android.content.ContentResolver;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.ThaiKi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by duong on 10/5/2017.
 */

public class ThaiKiAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<ThaiKi> arrThaiKis;

    public ThaiKiAdapter(ArrayList<ThaiKi> arrThaiKis) {
        this.arrThaiKis = arrThaiKis;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrThaiKis.size();
    }

    @Override
    public ThaiKi getItem(int i) {
        return arrThaiKis.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.list_view_thaiki, viewGroup, false);
            holder = new Holder();
            holder.txtThaiKi = view.findViewById(R.id.txtThaiKi);
            holder.imgThaiKi = view.findViewById(R.id.imgThaiKi);
            holder.imgCheck = view.findViewById(R.id.imgCheck);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.txtThaiKi.setText(arrThaiKis.get(i).getName());
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                App.getContext().getPackageName() + "/drawable/" + arrThaiKis.get(i).getImage());
        Picasso.with(App.getContext()).load(uri).into(holder.imgThaiKi);
        holder.imgCheck.setImageResource(R.drawable.nen);
        return view;
    }

    private class Holder {
        private ImageView imgThaiKi;
        private TextView txtThaiKi;
        private ImageView imgCheck;
    }
}
