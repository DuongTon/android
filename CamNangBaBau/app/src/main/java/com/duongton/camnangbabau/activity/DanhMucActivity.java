package com.duongton.camnangbabau.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.adapter.GridViewAdapter;

public class DanhMucActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private GridView gridView;
    private String [] nameMenu;
    private int [] imageMenu;
    private TextView tvTitle;
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_muc);
        initData();

    }

    private void initData() {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("Danh mục");
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        nameMenu = new String[]{"Thai kì", "Ăn uống", " Tiêm phòng", "Lịch khám thai",
                "Cần mua & Cần làm", "Đặt tên cho bé", "Đọc truyện", "Âm nhạc", "Lập lịch", "Hỏi đáp"};

        imageMenu = new int[]{R.drawable.thaikydefault, R.drawable.anuongdefault, R.drawable.tiemphongdefault,
                R.drawable.lichkhamthaidefault, R.drawable.canmuacanlamdefault, R.drawable.dattenchobedefault,
                R.drawable.doctruyendefault, R.drawable.amnhacdefault, R.drawable.laplichdefault,
                R.drawable.hoidapdefault
        };
        gridView = (GridView) findViewById(R.id.gridViewMenu);
        gridView.setOnItemClickListener(this);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, nameMenu, imageMenu);
        gridView.setAdapter(gridViewAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                Intent intent = new Intent(this, ThaiKyActivity.class);
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(this, AnUongActivity.class);
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this, TiemPhongActivity.class);
                startActivity(intent2);
                break;
            case 3:
                Intent intent3 = new Intent(this, KhamThaiActivity.class);
                startActivity(intent3);
                break;
            case 4:
                Intent intent4 = new Intent(this, CanMuaCanLamActivity.class);
                startActivity(intent4);
                break;
            case 5:
                Intent intent5 = new Intent(this, DatTenActivity.class);
                startActivity(intent5);
                break;
            case 6:
                Intent intent6 = new Intent(this, DocTruyenActivity.class);
                startActivity(intent6);
                break;
            case 7:
                Intent intent7 = new Intent(this, NgheNhacActivity.class);
                startActivity(intent7);
                break;
            case 8:
                Intent intent8 = new Intent(this, LapLichActivity.class);
                startActivity(intent8);
                break;
            case 9:
                Intent intent9 = new Intent(this, HoiDapActivity.class);
                startActivity(intent9);
                break;
        }
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
