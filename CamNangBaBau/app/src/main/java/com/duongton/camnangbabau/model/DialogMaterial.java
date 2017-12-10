package com.duongton.camnangbabau.model;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.inter.OnReceiveDataDatePickerListener;

/**
 * Created by Theson on 11/20/2017.
 */

public class DialogMaterial extends Dialog implements View.OnClickListener{

    private Button btnSelect;
    private Button btnCancel;

    private OnReceiveDataDatePickerListener onReceiveDataListener;

    public DialogMaterial(Context context) {
        super(context);
        initializeComponents();
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_material);
        btnSelect = findViewById(R.id.btn_select);
        btnCancel = findViewById(R.id.btn_cancel);
        btnSelect.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ấn ra ngoài tăt dilog đi
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_select:
              onReceiveDataListener.OnDataReceiveData(true);
                dismiss();
                break;
            case R.id.btn_cancel:
                Toast.makeText(getContext(), "Cancel", Toast.LENGTH_SHORT).show();
                dismiss();
                break;
            default:

        }
    }

    public void setOnReceiveDataListener(OnReceiveDataDatePickerListener onReceiveDataListener) {
        this.onReceiveDataListener = onReceiveDataListener;
    }
}
