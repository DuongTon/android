package com.duongton.camnangbabau.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.TiemPhongPageAdapter;

public class TiemPhongActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TiemPhongPageAdapter adapter;
    private ImageView imgPicture;
    private TextView tvTitle;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiem_phong);
        initializeComponents();
    }

    private void initializeComponents() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Tiêm phòng");
        tabLayout = (TabLayout) findViewById(R.id.tabLayoutTiemPhong);
        viewPager = (ViewPager) findViewById(R.id.viewPageTiemPhong);
        imgPicture = (ImageView) findViewById(R.id.imgPictureTiemPhong);
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new TiemPhongPageAdapter(fragmentManager);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new
        TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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
