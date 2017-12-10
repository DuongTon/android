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
import com.duongton.camnangbabau.adapter.DatTenAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.DatTen;

import java.util.ArrayList;

/**
 * Created by duong on 10/10/2017.
 */

public class BeGaiFragment extends Fragment {
    private ListView listView;
    private DatabaseManager databaseManager;
    private DatTenAdapter adapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_begai, container, false);
        initializeComponents();
        return view;
    }

    private void initializeComponents() {
        listView = view.findViewById(R.id.listViewBeGai);
        databaseManager = new DatabaseManager(App.getContext());
        String gioiTinh = "Nữ";
        ArrayList<DatTen> arrayList = databaseManager.getDataDatTen(gioiTinh);
        adapter = new DatTenAdapter(arrayList);
        listView.setAdapter(adapter);
    }
}
