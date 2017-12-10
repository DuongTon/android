package com.duongton.camnangbabau.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.activity.AnUongItemActivity;
import com.duongton.camnangbabau.adapter.AnUongAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.AnUong;

import java.util.ArrayList;

/**
 * Created by duong on 10/7/2017.
 */

public class ThucPhamFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listViewThucPham;
    private AnUongAdapter adapter;
    private DatabaseManager databaseManager;
    private View view;
    private  ArrayList<AnUong> arrayList;
    public static final String ID="ID";
    public static final String NAME="NAME";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_thuc_pham, container, false);
        initializeComponents();
        return view;
}

    private void initializeComponents() {
        listViewThucPham = view.findViewById(R.id.listViewThucPham);
        databaseManager = new DatabaseManager(App.getContext());
        String tenLoai = "Thực Phẩm";
        arrayList = databaseManager.getDataAnUong(tenLoai);
        adapter = new AnUongAdapter(arrayList);
        listViewThucPham.setAdapter(adapter);
        listViewThucPham.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("IDANUONG",arrayList.get(i).getId()+"");
        Intent intent = new Intent(App.getContext(), AnUongItemActivity.class);
       intent.putExtra(ID,arrayList.get(i).getId());
       intent.putExtra(NAME,"Thực phẩm");
        startActivity(intent);
    }
}
