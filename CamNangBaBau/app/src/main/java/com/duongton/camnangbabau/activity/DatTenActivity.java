package com.duongton.camnangbabau.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.DatTenPageAdapter;

public class DatTenActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DatTenPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_ten);
        initializeComponents();
    }

    private void initializeComponents() {
        viewPager = (ViewPager) findViewById(R.id.viewPageDatTen);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutDatTen);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new DatTenPageAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setBackgroundResource(R.drawable.tab_unselect);
    }
}
