package com.duongton.camnangbabau.fragment;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.duongton.camnangbabau.App;
import com.duongton.camnangbabau.R;
import com.duongton.camnangbabau.model.Vas;
import com.duongton.camnangbabau.receive.MyAlarmReceiver;
import com.duongton.camnangbabau.adapter.LapLichTiemChungAdapter;
import com.duongton.camnangbabau.inter.OnDataReceiveDateListener;
import com.duongton.camnangbabau.inter.OnReceiveDataTimePickerListener;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.DialogSelectDate;
import com.duongton.camnangbabau.model.DialogSelectHour;
import com.duongton.camnangbabau.model.LapLich;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LapLichTiemChungFragment extends Fragment implements View.OnClickListener,
        OnReceiveDataTimePickerListener, OnDataReceiveDateListener, LapLichTiemChungAdapter.OnItemClickDeleteListener{
    private View view;
    private TextView tvSelectHour;
    private TextView tvSelectDate;
    private TimePicker timePicker;
    private DatePicker datePicker;
    private DialogSelectHour dialogHour;
    private DialogSelectDate dialogSelectDate;
    private Button btnAdd;
    private Button btnCancel;
    private EditText edtNote;
    private DatabaseManager databaseManager;
    private ListView listView;
    private LapLichTiemChungAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_laplich_tiemchung, container, false);
        initializeComponents();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    private void initializeComponents() {
        databaseManager = new DatabaseManager(App.getContext());
        dialogHour = new DialogSelectHour(getActivity());
        dialogHour.setOnReceiveDataTimePickerListener(this);
        dialogSelectDate = new DialogSelectDate(getActivity());
        dialogSelectDate.setOnDataReceiveDateListener(this);
        timePicker = dialogHour.findViewById(R.id.time_picker);
        datePicker = dialogSelectDate.findViewById(R.id.datePicker);
        //timePicker.setIs24HourView(true);
        tvSelectHour = view.findViewById(R.id.tv_select_hour);
        tvSelectHour.setOnClickListener(this);
        tvSelectDate = view.findViewById(R.id.tv_select_date);
        tvSelectDate.setOnClickListener(this);
        btnAdd = view.findViewById(R.id.btn_add);
        btnCancel = view.findViewById(R.id.btn_cancel);
        edtNote = view.findViewById(R.id.edt_note);
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        listView = view.findViewById(R.id.list_tiemchung);
        initView();
        loadListView();
    }

    private void sendBroadcastReceiver() {
        Log.e("LapLichTiemChungFragment", "Oncreate Servico");
        Intent intent = new Intent(getActivity(), MyAlarmReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(getActivity(),MyAlarmReceiver.REQUEST_COE,intent, PendingIntent.FLAG_UPDATE_CURRENT );
        long firstMillis = System.currentTimeMillis(); //first run of alarm is immediate // aranca la palicacion
        int intervalMillis = 1 * 3 * 1000; //3 segundos
        AlarmManager alarm = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, intervalMillis, pIntent);
    }

    public void loadListView(){
        ArrayList<LapLich> arrayList = databaseManager.getDataLapLich(Vas.TYPE_TIEMCHUNG);
           adapter = new LapLichTiemChungAdapter(arrayList);
           adapter.setOnItemClickDeleteListener(this);
           if(arrayList !=null){
               listView.setAdapter(adapter);
           }else {
               listView.setAdapter(null);
           }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_select_hour:
                dialogHour.show();
                break;
            case R.id.tv_select_date:
                dialogSelectDate.show();
                break;
            case R.id.btn_add:
              checkInput();
                sendBroadcastReceiver();
                break;
            case R.id.btn_cancel:
                resetView();
                break;
            default:
        }
    }


   private void checkInput(){
       if(tvSelectHour.getText()=="" || tvSelectDate.getText()=="" || edtNote.getText().toString().isEmpty()){
           Toast.makeText(App.getContext(), "Vui lòng nhập đầy đủ thông tin !!!", Toast.LENGTH_SHORT).show();
       }else {
           addDatabase();
           resetView();
       }
   }
    private void resetView(){
        tvSelectDate.setText("");
        tvSelectHour.setText("");
        edtNote.setText("");
    }
    private void initView(){
        tvSelectDate.setHint("Nhập ngày");
        tvSelectHour.setHint("Giờ nhắc nhở");
        edtNote.setHint("ghi chú");
    }
    public void addDatabase(){
        String date = tvSelectDate.getText().toString();
        String hour = tvSelectHour.getText().toString();
        String note = edtNote.getText().toString();
        String type = Vas.TYPE_TIEMCHUNG;
        LapLich lapLich = new LapLich(date,hour, note, type, true);
        if(databaseManager.insertDataLapLich(lapLich)){
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
        }
        loadListView();
    }
    @Override
    public void OnDataReceiveData(boolean selectHour) {
        if(selectHour){
            int gio = timePicker.getCurrentHour();
            int phut = timePicker.getCurrentMinute();
            Calendar calendar = Calendar.getInstance();
            calendar.set(0,0,0,gio,phut);
            //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            tvSelectHour.setText(gio + ":" + convertHour(phut));
        }
    }
    private static String convertHour(int hour) {
        if (hour >= 10)
            return String.valueOf(hour);
        else
            return "0" + String.valueOf(hour);
    }

    @Override
    public void OnDataReceiveDate(boolean selectDate) {
        if(selectDate){
            String date = datePicker.getDayOfMonth() + "/" +  (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
            tvSelectDate.setText(date);
        }
    }

    @Override
    public void OnClickDelete(int position) {
        String id = position + "";
       if(databaseManager.deleteLapLich(id)){
           Toast.makeText(App.getContext(), "Delete thành công", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(App.getContext(), "Delete Error", Toast.LENGTH_SHORT).show();
       }
       loadListView();
    }
}
