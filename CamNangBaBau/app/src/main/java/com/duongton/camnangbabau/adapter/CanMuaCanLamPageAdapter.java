package com.duongton.camnangbabau.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.duongton.camnangbabau.fragment.CanMuaCanLamFragment;
import com.duongton.camnangbabau.fragment.ChuanBiSinhFragment;
import com.duongton.camnangbabau.fragment.MonAnFragment;
import com.duongton.camnangbabau.fragment.NhomChatFragment;
import com.duongton.camnangbabau.fragment.ThucPhamFragment;

/**
 * Created by duong on 10/6/2017.
 */

public class CanMuaCanLamPageAdapter extends FragmentStatePagerAdapter {
    public CanMuaCanLamPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new CanMuaCanLamFragment();
                break;
            case 1:
                fragment = new ChuanBiSinhFragment();
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
                title = "CẦN MUA & CẦN LÀM";
                break;
            case 1:
                title = "CHUẨN BỊ SINH";
                break;
            default:
        }
        return title;
    }
}
