package com.duongton.camnangbabau.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.duongton.camnangbabau.fragment.BeGaiFragment;
import com.duongton.camnangbabau.fragment.BeTraiFragment;
import com.duongton.camnangbabau.fragment.FragmentTongQuan;

/**
 * Created by duong on 10/6/2017.
 */

public class DatTenPageAdapter extends FragmentStatePagerAdapter {
    public DatTenPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragmentTongQuan();
                break;
            case 1:
                fragment = new BeTraiFragment();
                break;
            case 2:
                fragment = new BeGaiFragment();
                break;
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
                title = "TỔNG QUAN";
                break;
            case 1:
                title = "BÉ TRAI";
                break;
            case 2:
                title = "BÉ GÁI";
                break;
            default:
        }
        return title;
    }
}
