package com.theson.filemanagers.model;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.theson.filemanagers.R;


public class ItemDialogNewFolder extends Dialog implements View.OnClickListener {
    private EditText edtName;
    private OnClickButtonListener onClickButtonListener;

    public ItemDialogNewFolder(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_dialog_newfolder);
        initializeComponents();
    }
    private void initializeComponents() {
        edtName = (EditText) findViewById(R.id.edtName);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        Button btnCreate = (Button) findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("New Folder");
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCancel:
                dismiss();
                break;
            case R.id.btnCreate:
                onClickButtonListener.onClickButton(edtName.getText().toString());
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setOnClickButtonListener(OnClickButtonListener listener) {
        this.onClickButtonListener = listener;
    }

    public interface OnClickButtonListener {
        void onClickButton(String name);
    }
}
