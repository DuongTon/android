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
import com.duongton.camnangbabau.adapter.TongQuanAdapter;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.DatTen;

import java.util.ArrayList;

/**
 * Created by duong on 10/10/2017.
 */



public class FragmentTongQuan extends Fragment {
    private ListView listView;
    private DatabaseManager databaseManager;
    private TongQuanAdapter adapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tongquan, container, false);
        initializeComponents();
        return view;
    }

    private void initializeComponents() {
        listView = view.findViewById(R.id.listTongQuan);
        databaseManager = new DatabaseManager(App.getContext());
        ArrayList<DatTen> arrayList = databaseManager.getDataDatTenTongQuan();
        adapter = new TongQuanAdapter(arrayList);
        listView.setAdapter(adapter);
    }
}
