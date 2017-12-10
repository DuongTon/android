package com.duongton.camnangbabau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duongton.camnangbabau.R;

/**
 * Created by duong on 10/4/2017.
 */

public class FragmentMain extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
