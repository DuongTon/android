package com.theson.filemanagers.model;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.theson.filemanagers.R;

public class ItemDialog extends Dialog implements View.OnClickListener {

    public static final String TXT_RENAME = "rename";
    public static final String TXT_COPY = "copy";
    public static final String TXT_DELETE = "delete";
    public static final String TXT_CUT = "cut";

    private OnReceiveDataListener onReceiveDataListener;

    public ItemDialog(Context context) {
        super(context);
        initializeComponents();
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_dialog);
        TextView txtCopy = (TextView) findViewById(R.id.txtCopy);
        TextView txtCut = (TextView) findViewById(R.id.txtCut);
        TextView txtDelete = (TextView) findViewById(R.id.txtDelete);
        TextView txtRename = (TextView) findViewById(R.id.txtRename);
        txtCopy.setOnClickListener(this);
        txtCut.setOnClickListener(this);
        txtDelete.setOnClickListener(this);
        txtRename.setOnClickListener(this);
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
            case R.id.txtCopy:
                dismiss();
                onReceiveDataListener.OnDataReceiveData(TXT_COPY);
                break;
            case R.id.txtCut:
                dismiss();
                onReceiveDataListener.OnDataReceiveData(TXT_CUT);
                break;
            case R.id.txtDelete:
                dismiss();
                onReceiveDataListener.OnDataReceiveData(TXT_DELETE);
                break;
            case R.id.txtRename:
                dismiss();
                onReceiveDataListener.OnDataReceiveData(TXT_RENAME);
                break;
            default:
                break;
        }
    }

    public void setOnReceiveDataListener(OnReceiveDataListener listener) {
        this.onReceiveDataListener = listener;
    }

    public interface OnReceiveDataListener {
        void OnDataReceiveData(String data);
    }
}
