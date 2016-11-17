package theson.com.ailatrieuphu.model;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import theson.com.ailatrieuphu.R;

public class DialogExitGame extends Dialog implements View.OnClickListener {

    public static final String TXT_OK = "ok";

    private OnReceiveDataListener onReceiveDataListener;

    public DialogExitGame(Context context) {
        super(context);
        initializeComponents();
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_exit_game);
        Button btnCancel = (Button) findViewById(R.id.btn_cancel);
        Button btnOk = (Button) findViewById(R.id.btn_ok);
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.btn_ok:
                onReceiveDataListener.OnDataReceiveData(TXT_OK);
                dismiss();
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
