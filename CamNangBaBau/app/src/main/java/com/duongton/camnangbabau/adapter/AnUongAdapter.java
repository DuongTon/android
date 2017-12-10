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
import android.widget.Toast;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.AnUong;
import com.duongton.camnangbabau.model.ThaiKi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by duong on 10/7/2017.
 */

public class AnUongAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ArrayList<AnUong> arrayList;

    public AnUongAdapter(ArrayList<AnUong> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public AnUong getItem(int i) {
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
            view = inflater.inflate(R.layout.list_view_anuong, viewGroup, false);
            holder = new ViewHolder();
            holder.txtName = view.findViewById(R.id.txtThaiKi);
            holder.imgPicture = view.findViewById(R.id.imgThaiKi);
            holder.imgCheck = view.findViewById(R.id.imgCheck);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        AnUong anUong = arrayList.get(i);
        holder.txtName.setText(anUong.getName());
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                App.getContext().getPackageName() + "/drawable/" + anUong.getImage());
        Picasso.with(App.getContext()).load(uri).into(holder.imgPicture);
        String kiemTra = "Nên";
        Log.d("KIEMTRAANUONG",anUong.getName());
        if(anUong.getKiemTra().equals(kiemTra)){
            holder.imgCheck.setImageResource(R.drawable.nen);
        }else {
            holder.imgCheck.setImageResource(R.drawable.khongnen);
        }
        return view;
    }

    private class ViewHolder{
        private TextView txtName;
        private ImageView imgPicture;
        private ImageView imgCheck;
    }
}
