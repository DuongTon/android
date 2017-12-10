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
import com.duongton.camnangbabau.adapter.CanMuaCanLamAdapter;
import com.duongton.camnangbabau.adapter.CanMuaCanLamPageAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.CanMuaCanLam;

import java.util.ArrayList;

/**
 * Created by duong on 10/7/2017.
 */

public class CanMuaCanLamFragment extends Fragment {
    private ListView listView;
    private CanMuaCanLamAdapter adapter;
    private DatabaseManager databaseManager;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_canmua_canlam, container, false);
        initializeComponents();
        return view;
    }

    private void initializeComponents() {
        listView = view.findViewById(R.id.listViewCanMua);
        databaseManager = new DatabaseManager(App.getContext());
        ArrayList<CanMuaCanLam> arrayList = databaseManager.getDataCanMuaCanLam();
        adapter = new CanMuaCanLamAdapter(arrayList);
        listView.setAdapter(adapter);

    }
}
