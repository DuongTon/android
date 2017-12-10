package com.duongton.camnangbabau.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.inter.OnReceiveDataDatePickerListener;
import com.duongton.camnangbabau.model.DialogMaterial;
import com.duongton.camnangbabau.model.Vas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnReceiveDataDatePickerListener {

    private  final String TAG = getClass().getSimpleName();
    private Button btnNext;
    private TextView tvSelectDate;
    private TextView tvCalculator;
    private TextView tvDisplay;
    private DialogMaterial dialog;
    private DatePicker datePicker;
    private Calendar cal;
    private SharedPreferences sharedPreferencesDate;
    private SharedPreferences sharedPreferencesShowDialod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponents();
        checkStartDialog();
    }

    private void initializeComponents() {
        dialog = new DialogMaterial(this);
        dialog.setOnReceiveDataListener(this);
        tvSelectDate = (TextView) findViewById(R.id.tv_select_date);
        tvCalculator = (TextView) findViewById(R.id.tv_calculator);
        tvDisplay = (TextView) findViewById(R.id.tv_display);
        tvSelectDate.setOnClickListener(this);
        datePicker = dialog.findViewById(R.id.datePicker);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        setupDateTextView();
        //delete();
        tvCalculator.setText(calculatorDueDate());
        tvDisplay.setText(calculatorFetalAge());
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
        saveBooleanSharePreferences(true);
    }

    private void checkStartDialog(){
      sharedPreferencesShowDialod = getSharedPreferences(Vas.SHARED_PREFERENCES_BOOLEAN, Context.MODE_PRIVATE);
        boolean check = sharedPreferencesShowDialod.getBoolean(Vas.BOOLEAN, false);
        if(!check){
            dialog.show();
            initDataDatePicker();
        }

    }

    private void initDataDatePicker(){
        String date = tvSelectDate.getText().toString();
        String[] selectDate = date.split("/");
        int day = Integer.parseInt(selectDate[0]);
        final int month = Integer.parseInt(selectDate[1]) - 1;
        int year = Integer.parseInt(selectDate[2]);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(MainActivity.this, year + " " + (monthOfYear + 1) + " " + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });
        Log.e(TAG, "Year: " + year +" Month: " + month + " Day: " + day);
    }

   private String calculatorDueDate(){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       String strDate = tvSelectDate.getText().toString();
       String output = null;
       try {
           Date date = sdf.parse(strDate);
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(date); // Now use today date.
           calendar.add(Calendar.WEEK_OF_MONTH, 40); // Adding 40 week
            output = sdf.format(calendar.getTime());
           return output;
       } catch (ParseException e) {
           e.printStackTrace();
       }
      return output;

   }

   public String calculatorFetalAge(){
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       String strDate = tvSelectDate.getText().toString();
       String title = "";
       try {
           Date date1 = sdf.parse(strDate);
           //Date date2 = sdf.parse(Strtt);

           Date nowDate = Calendar.getInstance().getTime();
           long timeInMiliSecond = nowDate.getTime() - date1.getTime();
           System.out.println(timeInMiliSecond);
           int  totalDay = (int) (timeInMiliSecond / (1000l * 60 * 60 * 24));
           int week= totalDay /7;
           int day = totalDay % 7;
           title = " Thai nhi được " + week + " tuần " + day + " ngày tuổi.";

       } catch (ParseException e) {
           e.printStackTrace();
       }

       return title;
   }

    private void setupDateTextView() {
         sharedPreferencesDate = getSharedPreferences(Vas.SHARED_PREFERENCES_DATE, Context.MODE_PRIVATE);
        String date = sharedPreferencesDate.getString(Vas.SELECT_DATE, null);
        if(date ==null){
            cal=Calendar.getInstance();
            SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String strDate= sdf.format(cal.getTime());
            tvSelectDate.setText(strDate);
        }else {
            tvSelectDate.setText(date);
        }
    }

    private void delete(){
         sharedPreferencesDate = getSharedPreferences(Vas.SHARED_PREFERENCES_BOOLEAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesDate.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_select_date:
               dialog.show();
                initDataDatePicker();
                break;
            case R.id.btnNext:
                Intent intent = new Intent(this, DanhMucActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }



    @Override
    public void OnDataReceiveData(boolean click) {
        if(click){
            String date = padding_str(datePicker.getDayOfMonth()) + "/" +  padding_str((datePicker.getMonth() + 1)) + "/" + datePicker.getYear();
            tvSelectDate.setText(date);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = tvSelectDate.getText().toString();
            try {
                Date checkDate = sdf.parse(strDate);
                Date nowDate = new Date();
                if(checkDate.after(nowDate) || checkDate.equals(nowDate)){
                    tvDisplay.setText("Ngày kinh cuối phải nhỏ hơn ngày hiện tại");
                    tvDisplay.setTextColor(Color.RED);
                }else {
                    tvCalculator.setText(calculatorDueDate());
                    tvDisplay.setText(calculatorFetalAge());
                    tvDisplay.setTextColor(Color.BLACK);
                    saveSelectDateSharedPreferences();
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private static String padding_str(int c) {

        if (c >= 10)
            return String.valueOf(c);

        else

            return "0" + String.valueOf(c);
    }

    private void saveSelectDateSharedPreferences() {
         sharedPreferencesDate = getSharedPreferences(Vas.SHARED_PREFERENCES_DATE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesDate.edit();
        editor.putString(Vas.SELECT_DATE, tvSelectDate.getText().toString());
        editor.apply();
    }

    private void saveBooleanSharePreferences(boolean check){
        sharedPreferencesShowDialod = getSharedPreferences(Vas.SHARED_PREFERENCES_BOOLEAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesShowDialod.edit();
        editor.putBoolean(Vas.BOOLEAN, check);
        editor.apply();
    }
}
