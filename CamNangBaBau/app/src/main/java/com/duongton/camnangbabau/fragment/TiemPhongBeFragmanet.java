package com.duongton.camnangbabau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.TiemPhongAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.TiemPhong;

import java.util.ArrayList;

/**
 * Created by duong on 10/7/2017.
 */

public class TiemPhongBeFragmanet extends Fragment {

    private ListView listView;
    private TiemPhongAdapter adapter;
    private DatabaseManager databaseManager;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tiemphong_be, container, false);
        initializeComponents();
        return view;

    }

    private void initializeComponents() {
        listView = view.findViewById(R.id.listViewTiemPhongBe);
        databaseManager = new DatabaseManager(App.getContext());
        String nguoiTiem = "Bé";
        ArrayList<TiemPhong> arrayList = databaseManager.getDataTiemPhong(nguoiTiem);
        adapter = new TiemPhongAdapter(arrayList);
        listView.setAdapter(adapter);
    }
}
