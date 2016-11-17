package com.theson.filemanagers.model;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.theson.filemanagers.R;
import com.theson.filemanagers.activity.MainActivity;

public class ItemDialogRename extends Dialog implements View.OnClickListener{

    private EditText edtRename;
    private String fileName;
    private OnClickSaveListener onClickSaveListener;
    public ItemDialogRename(Context context, String fileName) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_dialog_rename);
        this.fileName = fileName;
        initializeComponents();
    }
    private void initializeComponents() {
        edtRename = (EditText) findViewById(R.id.edtRename);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        Button btnSave = (Button) findViewById(R.id.btnSave);
        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        edtRename.setText(fileName);
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
            case R.id.btnCancel:
                dismiss();
                break;

            case R.id.btnSave:
                onClickSaveListener.OnClickSave(edtRename.getText().toString());
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setOnClickSaveListener(OnClickSaveListener listener) {
        this.onClickSaveListener = listener;
    }

    public interface OnClickSaveListener{
        void OnClickSave(String name);
    }

}
