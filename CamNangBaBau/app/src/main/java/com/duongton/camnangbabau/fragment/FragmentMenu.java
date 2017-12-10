package com.duongton.camnangbabau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.GridViewAdapter;

/**
 * Created by duong on 10/4/2017.
 */

public class FragmentMenu extends Fragment {
    private GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        gridView = view.findViewById(R.id.gridViewMenu);
        initData();
        return view;
    }

    private void initData() {
        String [] nameMenu = {"Thai kì", "Ăn uống", " Tiêm phòng", "Lịch khám thai",
                "Cần mua & Cần làm", "Đặt tên cho bé", "Đọc truyện", "Âm nhạc","Lập lịch", "Hỏi đáp"};

        int [] imageMenu ={R.drawable.thaikydefault, R.drawable.anuongdefault,R.drawable.tiemphongdefault,
        R.drawable.lichkhamthaidefault, R.drawable.canmuacanlamdefault, R.drawable.dattenchobedefault,
                R.drawable.doctruyendefault, R.drawable.amnhacdefault, R.drawable.laplichdefault,
                R.drawable.hoidapdefault
        };

        GridViewAdapter gridViewAdapter = new GridViewAdapter(getActivity(), nameMenu, imageMenu);
        gridView.setAdapter(gridViewAdapter);
    }
}
