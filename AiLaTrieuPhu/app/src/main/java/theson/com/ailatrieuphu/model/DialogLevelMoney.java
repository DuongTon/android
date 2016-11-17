package theson.com.ailatrieuphu.model;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.Window;
import android.widget.TableRow;
import android.widget.TextView;

import theson.com.ailatrieuphu.R;

public class DialogLevelMoney extends Dialog implements View.OnClickListener {

    private static final int QUESTION_01 = 1;
    private static final int QUESTION_02 = 2;
    private static final int QUESTION_03 = 3;
    private static final int QUESTION_04 = 4;
    private static final int QUESTION_05 = 5;
    private static final int QUESTION_06 = 6;
    private static final int QUESTION_07 = 7;
    private static final int QUESTION_08 = 8;
    private static final int QUESTION_09 = 9;
    private static final int QUESTION_10 = 10;
    private static final int QUESTION_11 = 11;
    private static final int QUESTION_12 = 12;
    private static final int QUESTION_13 = 13;
    private static final int QUESTION_14 = 14;
    private static final int QUESTION_15 = 15;

    private TableRow table01;
    private TableRow table02;
    private TableRow table03;
    private TableRow table04;
    private TableRow table05;
    private TableRow table06;
    private TableRow table07;
    private TableRow table08;
    private TableRow table09;
    private TableRow table10;
    private TableRow table11;
    private TableRow table12;
    private TableRow table13;
    private TableRow table14;
    private TableRow table15;

    private TextView txtSkip;

    public DialogLevelMoney(Context context) {
        super(context);
        initializeComponents();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    private void initializeComponents() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_level_money);
        table01 = (TableRow) findViewById(R.id.table_01);
        table02 = (TableRow) findViewById(R.id.table_02);
        table03 = (TableRow) findViewById(R.id.table_03);
        table04 = (TableRow) findViewById(R.id.table_04);
        table05 = (TableRow) findViewById(R.id.table_05);
        table06 = (TableRow) findViewById(R.id.table_06);
        table07 = (TableRow) findViewById(R.id.table_07);
        table08 = (TableRow) findViewById(R.id.table_08);
        table09 = (TableRow) findViewById(R.id.table_09);
        table10 = (TableRow) findViewById(R.id.table_10);
        table11 = (TableRow) findViewById(R.id.table_11);
        table12 = (TableRow) findViewById(R.id.table_12);
        table13 = (TableRow) findViewById(R.id.table_13);
        table14 = (TableRow) findViewById(R.id.table_14);
        table15 = (TableRow) findViewById(R.id.table_15);

        txtSkip = (TextView) findViewById(R.id.txt_skip);
        txtSkip.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setBackgroundLevelMoney(int index) {
        switch (index) {
            case QUESTION_01:
                table01.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;
            case QUESTION_02:
                table01.setBackground(null);
                table02.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;
            case QUESTION_03:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;
            case QUESTION_04:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;
            case QUESTION_05:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;

            case QUESTION_06:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;

            case QUESTION_07:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;

            case QUESTION_08:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;

            case QUESTION_09:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;


            case QUESTION_10:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;

            case QUESTION_11:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;

            case QUESTION_12:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackground(null);
                break;
            case QUESTION_13:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table14.setBackground(null);
                table15.setBackground(null);
                break;

            case QUESTION_14:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                table15.setBackground(null);
                break;
            case QUESTION_15:
                table01.setBackground(null);
                table02.setBackground(null);
                table03.setBackground(null);
                table04.setBackground(null);
                table05.setBackground(null);
                table06.setBackground(null);
                table07.setBackground(null);
                table08.setBackground(null);
                table09.setBackground(null);
                table10.setBackground(null);
                table11.setBackground(null);
                table12.setBackground(null);
                table13.setBackground(null);
                table14.setBackground(null);
                table15.setBackgroundResource(R.drawable.atp__activity_player_image_money_curent);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_skip:
                dismiss();
                break;
        }
    }
}
