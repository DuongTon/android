package com.duongton.camnangbabau.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.DocTruyen;

import java.util.ArrayList;

/**
 * Created by duong on 10/11/2017.
 */

public class DocTruyenNoiDungAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private ArrayList<DocTruyen> arrayList;

    public DocTruyenNoiDungAdapter(ArrayList<DocTruyen> arrayList) {
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(App.getContext());
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public DocTruyen getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder;
        if(view == null){
            view = inflater.inflate(R.layout.list_view_doc_truyen_noidung, viewGroup, false);
            viewholder = new Viewholder();
            viewholder.txtNoiDung = view.findViewById(R.id.txtDocTruyenNoiDung);
            view.setTag(viewholder);
        }else {
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.txtNoiDung.setText(arrayList.get(i).getNoiDung());
        return view;
    }

    private class Viewholder{
        TextView txtNoiDung;
    }
}
