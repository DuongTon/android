package com.duongton.camnangbabau.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.CanMuaCanLamPageAdapter;

public class CanMuaCanLamActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private CanMuaCanLamPageAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_can_mua_can_lam);
        initializeComponents();
    }

    private void initializeComponents() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutCanMuaCanLam);
        viewPager = (ViewPager) findViewById(R.id.viewPageCanMuaCanLam);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new CanMuaCanLamPageAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new
        TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setBackgroundResource(R.drawable.tab_unselect);
    }
}
