package com.duongton.camnangbabau.model;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.inter.OnDataReceiveDateListener;
import com.duongton.camnangbabau.inter.OnReceiveDataTimePickerListener;

/**
 * Created by Theson on 11/21/2017.
 */

public class DialogSelectDate extends Dialog implements View.OnClickListener {


    private Button btnSelect;
    private Button btnCancel;
    private OnDataReceiveDateListener onDataReceiveDateListener;

    public DialogSelectDate(@NonNull Context context) {
        super(context);
        initializeComponents();
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_date);
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
                onDataReceiveDateListener.OnDataReceiveDate(true);
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
        }
    }

    public void setOnDataReceiveDateListener(OnDataReceiveDateListener onDataReceiveDateListener) {
        this.onDataReceiveDateListener = onDataReceiveDateListener;
    }
}
