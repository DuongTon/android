package com.duongton.camnangbabau.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.duongton.camnangbabau.fragment.MonAnFragment;
import com.duongton.camnangbabau.fragment.NhomChatFragment;
import com.duongton.camnangbabau.fragment.ThucPhamFragment;
import com.duongton.camnangbabau.fragment.TiemPhongBeFragmanet;
import com.duongton.camnangbabau.fragment.TiemPhongMeFragmanet;

/**
 * Created by duong on 10/6/2017.
 */

public class TiemPhongPageAdapter extends FragmentStatePagerAdapter {
    public TiemPhongPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new TiemPhongMeFragmanet();
                break;
            case 1:
                fragment = new TiemPhongBeFragmanet();
                break;
            default:
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position){
            case 0:
                title = "CHO MẸ";
                break;
            case 1:
                title = "CHO BÉ";
                break;
            default:
        }
        return title;
    }
}
