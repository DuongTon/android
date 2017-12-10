package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.LapLich;
import com.duongton.camnangbabau.model.Vas;

import java.util.ArrayList;

/**
 * Created by Theson on 11/22/2017.
 */

public class LapLichKhamThaiAdapter extends BaseAdapter {
    private ArrayList<LapLich> arrayList;
    private LayoutInflater inflater;
    private OnItemClickDeleteListener onItemClickDeleteListener;

    public LapLichKhamThaiAdapter(ArrayList<LapLich> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public LapLich getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if(view ==null){
            holder = new Holder();
            view = inflater.inflate(R.layout.list_view_item_laplich_khamthai,viewGroup, false);
            holder.tvDate = view.findViewById(R.id.tv_date);
            holder.tvHour = view.findViewById(R.id.tv_hour);
            holder.imgDelete = view.findViewById(R.id.img_delete);
            view.setTag(holder);
        }else{
            holder = (Holder) view.getTag();
        }
       // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       // String date = sdf.format(arrayList.get(i).getDate());
        String note = arrayList.get(i).getDate() + " : " + arrayList.get(i).getNote();
        holder.tvDate.setText(note);
        holder.tvHour.setText(Vas.TEXT + arrayList.get(i).getTime());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickDeleteListener.OnClickDelete(arrayList.get(i).getId());
            }
        });
        return view;
    }

    private class Holder{
        TextView tvDate;
        TextView tvHour;
        ImageView imgDelete;
    }

    public void setOnItemClickDeleteListener(OnItemClickDeleteListener onItemClickDeleteListener) {
        this.onItemClickDeleteListener = onItemClickDeleteListener;
    }

    public interface OnItemClickDeleteListener{
        void OnClickDelete(int position);
    }
}
