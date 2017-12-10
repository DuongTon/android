package com.duongton.camnangbabau.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.AnUongPageAdapter;

public class AnUongActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AnUongPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_an_uong);
        initializeComponents();
    }

    private void initializeComponents() {
        viewPager = (ViewPager) findViewById(R.id.viewPageAnUong);
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutAnUong);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new AnUongPageAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) );
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setBackgroundResource(R.drawable.tab_unselect);

    }
}
