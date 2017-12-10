package com.duongton.camnangbabau.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.LapLichPageAdapter;

public class LapLichActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private LapLichPageAdapter adapter;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_lich);
        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Lập lịch");
        viewPager = (ViewPager) findViewById(R.id.viewpageLapLich);
        tabLayout = (TabLayout) findViewById(R.id.tabLapLich);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new LapLichPageAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout) );
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setBackgroundResource(R.drawable.tab_unselect);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                finish();
                break;
            default:
        }
    }
}
