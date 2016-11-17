package theson.com.ailatrieuphu.model;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import theson.com.ailatrieuphu.R;

public class DialogCall extends Dialog implements View.OnClickListener {

    private RelativeLayout relativeLayoutCall;
    private LinearLayout layout;
    private LinearLayout layoutCall01;
    private LinearLayout layoutCall02;
    private LinearLayout layoutCall03;
    private LinearLayout layoutCall04;

    private TextView txtResult;

    private Button btnClose;
    private String answer;

    public DialogCall(Context context) {
        super(context);
        initializeComponents();
    }

    private void initializeComponents() {
        answer = "D";
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_call);
        getWindow().getAttributes().windowAnimations = R.style.Dialog_Call_Help;
        ImageView btnCall01 = (ImageView) findViewById(R.id.btn_call_01);
        ImageView btnCall02 = (ImageView) findViewById(R.id.btn_call_02);
        ImageView btnCall03 = (ImageView) findViewById(R.id.btn_call_03);
        ImageView btnCall04 = (ImageView) findViewById(R.id.btn_call_04);
        btnCall01.setOnClickListener(this);
        btnCall02.setOnClickListener(this);
        btnCall03.setOnClickListener(this);
        btnCall04.setOnClickListener(this);
        btnClose = (Button) findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        relativeLayoutCall = (RelativeLayout) findViewById(R.id.relative_layout_call);
        layoutCall01 = (LinearLayout) findViewById(R.id.layout_call_01);
        layoutCall02 = (LinearLayout) findViewById(R.id.layout_call_02);
        layoutCall03 = (LinearLayout) findViewById(R.id.layout_call_03);
        layoutCall04 = (LinearLayout) findViewById(R.id.layout_call_04);
        layout = (LinearLayout) findViewById(R.id.layout);
        relativeLayoutCall.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
        layoutCall01.setVisibility(View.GONE);
        layoutCall02.setVisibility(View.GONE);
        layoutCall03.setVisibility(View.GONE);
        layoutCall04.setVisibility(View.GONE);
        txtResult = (TextView) findViewById(R.id.txt_result);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View v) {
        String text= "Theo tôi phương án đúng là: " + answer;
        switch (v.getId()) {
            case R.id.btn_call_01:
                relativeLayoutCall.setVisibility(View.GONE);
                layoutCall01.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                txtResult.setText(text);
                break;
            case R.id.btn_call_02:
                relativeLayoutCall.setVisibility(View.GONE);
                layoutCall02.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                txtResult.setText(text);
                break;
            case R.id.btn_call_03:
                relativeLayoutCall.setVisibility(View.GONE);
                layoutCall03.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                txtResult.setText(text);
                break;
            case R.id.btn_call_04:
                relativeLayoutCall.setVisibility(View.GONE);
                layoutCall04.setVisibility(View.VISIBLE);
                layout.setVisibility(View.VISIBLE);
                txtResult.setText(text);
                break;
            case R.id.btn_close:
                dismiss();
                break;
            default:
                break;
        }
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
