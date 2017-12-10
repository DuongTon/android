package com.duongton.camnangbabau.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.duongton.camnangbabau.fragment.LapLichKhacFragment;
import com.duongton.camnangbabau.fragment.LapLichKhamThaiFragment;
import com.duongton.camnangbabau.fragment.LapLichTiemChungFragment;

/**
 * Created by Theson on 11/21/2017.
 */

public class LapLichPageAdapter extends FragmentStatePagerAdapter {
    public LapLichPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new LapLichTiemChungFragment();
                break;
            case 1:
                fragment = new LapLichKhamThaiFragment();
                break;
            case 2:
                fragment = new LapLichKhacFragment();
                default:
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      String title = null;
        switch (position){
            case 0:
                title = "TIÊM CHỦNG";
                break;
            case 1:
                title = "KHÁM THAI";
                break;
            case 2:
                title = "KHÁC";
                break;
            default:
        }
        return title;
    }

}
