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
import com.duongton.camnangbabau.adapter.CanMuaCanLamPageAdapter;
import com.duongton.camnangbabau.adapter.ChuanBiSinhAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.CanMuaCanLam;
import com.duongton.camnangbabau.model.ChuanBiSinh;

import java.util.ArrayList;

/**
 * Created by duong on 10/7/2017.
 */

public class ChuanBiSinhFragment extends Fragment {
    private ListView listView;
    private ChuanBiSinhAdapter adapter;
    private DatabaseManager databaseManager;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_chuanbisinh, container, false);
        initializeComponents();
        return view;
    }

    private void initializeComponents() {
        listView = view.findViewById(R.id.listViewChuanBiSinh);
        databaseManager = new DatabaseManager(App.getContext());
        String nguoiCan = "Mẹ";
        ArrayList<Object> arrayList = databaseManager.getDataChuanBiSinh(nguoiCan);
        adapter = new ChuanBiSinhAdapter(arrayList);
        listView.setAdapter(adapter);
    }
}
