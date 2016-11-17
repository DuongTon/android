package theson.com.ailatrieuphu.model;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import theson.com.ailatrieuphu.R;

public class DialogEndTime extends Dialog implements View.OnClickListener {

    public DialogEndTime(Context context) {
        super(context);
        initializeComponents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        setCancelable(false);
        getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_end_time);
        Button btnClose= (Button) findViewById(R.id.btnClose);
        btnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClose:
                dismiss();
                break;
            default:
                break;
        }
    }
}
