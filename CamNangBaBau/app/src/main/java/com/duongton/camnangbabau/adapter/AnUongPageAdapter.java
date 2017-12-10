package com.duongton.camnangbabau.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.duongton.camnangbabau.fragment.MonAnFragment;
import com.duongton.camnangbabau.fragment.NhomChatFragment;
import com.duongton.camnangbabau.fragment.ThucPhamFragment;

/**
 * Created by duong on 10/6/2017.
 */

public class AnUongPageAdapter extends FragmentStatePagerAdapter {
    public AnUongPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new ThucPhamFragment();
                break;
            case 1:
                fragment = new MonAnFragment();
                break;
            case 2:
                fragment = new NhomChatFragment();
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
                title = "THỤC PHẨM";
                break;
            case 1:
                title = "MÓN ĂN";
                break;
            case 2:
                title = "NHÓM CHẤT";
                break;
            default:
        }
        return title;
    }
}
