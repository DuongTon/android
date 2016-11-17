package com.theson.filemanagers.model;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.theson.filemanagers.R;

public class ItemDialogHome extends Dialog implements View.OnClickListener {

    public static final String TXT_NEW_FOLDER = "newFolder";
    public static final String TXT_PASTE = "paste";
    private TextView txtPaste;

    public TextView getTxtPaste() {
        return txtPaste;
    }

    private OnReceiveDataListener onReceiveDataListener;

    public ItemDialogHome(Context context) {
        super(context);
        initializeComponents();
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_dialog_home);
        TextView txtNewFolder = (TextView) findViewById(R.id.txtNewFolder);
        TextView txtSort = (TextView) findViewById(R.id.txtSort);
        txtPaste = (TextView) findViewById(R.id.txtPaste);
        txtPaste.setOnClickListener(this);
        txtNewFolder.setOnClickListener(this);
        txtSort.setOnClickListener(this);
        txtPaste.setEnabled(false);
        txtPaste.setTextColor(0xffababab);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtNewFolder:
                dismiss();
                onReceiveDataListener.OnReceiveData(TXT_NEW_FOLDER);
                break;
            case R.id.txtSort:
                dismiss();
                break;
            case R.id.txtPaste:
                dismiss();
                onReceiveDataListener.OnReceiveData(TXT_PASTE);
                break;
            default:
                break;
        }
    }

    public void setOnReceiveDataListener(OnReceiveDataListener listener) {
        this.onReceiveDataListener = listener;
    }

    public interface OnReceiveDataListener {
        void OnReceiveData(String data);
    }

}
