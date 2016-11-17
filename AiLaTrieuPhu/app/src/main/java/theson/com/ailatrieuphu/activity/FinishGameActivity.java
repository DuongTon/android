package theson.com.ailatrieuphu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import theson.com.ailatrieuphu.R;

public class FinishGameActivity extends AppCompatActivity {

    private TextView txtMoneyVND;
    private TextView txtNumberFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_game);
        initializeComponents();
    }

    private void initializeComponents() {
        txtMoneyVND = (TextView) findViewById(R.id.txt_money_vnd);
        txtNumberFinish = (TextView) findViewById(R.id.txt_number_finish);
        Intent intentResult = getIntent();
        int number = Integer.parseInt(intentResult.getStringExtra(PlayActivity.KEY_NUMBER_QUESTION));
        String text = "Bạn đã vượt qua câu hỏi số " + number;
        String result;
        if(number <5){
            result ="0 VND";
        }else if(number<10){
            result = "2,000,000  VND";
        }else if(number<15){
            result = "22,000,000 VND";
        }else {
            result = "150,000,000 VND";
        }
        txtNumberFinish.setText(text);
        txtMoneyVND.setText(result);
    }
}
