package com.duongton.camnangbabau.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

public class MonAnFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listViewMonAn;
    private DatabaseManager databaseManager;
    private AnUongAdapter adapter;
    private View view;
    private ArrayList<AnUong> arrayList;
    public static final String ID="ID";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mon_an, container, false);
        initializeComponents();
        return view;
    }

    private void initializeComponents() {
        listViewMonAn = view.findViewById(R.id.listViewMonAn);
        databaseManager = new DatabaseManager(App.getContext());
        String tenLoai = "Món Ăn";
        arrayList = databaseManager.getDataAnUong(tenLoai);
        adapter = new AnUongAdapter(arrayList);
        listViewMonAn.setAdapter(adapter);
        listViewMonAn.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(App.getContext(), AnUongItemActivity.class);
        intent.putExtra(ID,arrayList.get(i).getId());
        startActivity(intent);
    }
}
