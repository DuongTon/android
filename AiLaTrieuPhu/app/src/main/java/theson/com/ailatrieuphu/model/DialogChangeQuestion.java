package theson.com.ailatrieuphu.model;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import theson.com.ailatrieuphu.R;

public class DialogChangeQuestion extends Dialog implements View.OnClickListener {

    public static final int OK = 1;
    public static final int CANCEL = -1;
    public static final String TXT_OK = "TXT_OK";

    private int isChange;
    private OnReceiveNextDataListener onReceiveNextDataListener;
    public DialogChangeQuestion(Context context) {
        super(context);
        initializeComponents();
    }

    public int getIsChange() {
        return isChange;
    }

    public void setIsChange(int isChange) {
        this.isChange = isChange;
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_change_question);
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
                isChange = CANCEL;
                dismiss();
                break;
            case R.id.btn_ok:
                isChange = OK;
                onReceiveNextDataListener.OnChangeDataReceiveData(TXT_OK);
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setOnReceiveNextDataListener(OnReceiveNextDataListener onReceiveNextDataListener) {
        this.onReceiveNextDataListener = onReceiveNextDataListener;
    }

    public interface OnReceiveNextDataListener {
        void OnChangeDataReceiveData(String data);
    }
}
