package com.duongton.camnangbabau.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.duongton.camnangbabau.adapter.LapLichKhamThaiAdapter;
import com.duongton.camnangbabau.inter.OnDataReceiveDateListener;
import com.duongton.camnangbabau.inter.OnReceiveDataTimePickerListener;
import com.duongton.camnangbabau.manager.DatabaseManager;
import com.duongton.camnangbabau.model.DialogSelectDate;
import com.duongton.camnangbabau.model.DialogSelectHour;
import com.duongton.camnangbabau.model.LapLich;
import com.duongton.camnangbabau.model.Vas;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Theson on 11/21/2017.
 */

public class LapLichKhamThaiFragment extends Fragment implements View.OnClickListener,
        OnDataReceiveDateListener,
        OnReceiveDataTimePickerListener, LapLichKhamThaiAdapter.OnItemClickDeleteListener{
    private View view;
    private TextView tvSelectDate;
    private TextView tvSelectHour;
    private EditText edtNote;
    private Button btnAdd;
    private Button btnCancel;
    private DialogSelectDate dialogSelectDate;
    private DialogSelectHour dialogSelectHour;
    private DatePicker datePicker;
    private TimePicker timePicker;
    
    private LapLichKhamThaiAdapter adapter;
    private ListView listView;
    private DatabaseManager databaseManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_laplich_khamthai, container, false);
        initializeComponents();
        return view;
    }

    private void initializeComponents() {
        databaseManager = new DatabaseManager(App.getContext());
        tvSelectDate = view.findViewById(R.id.tv_select_date);
        tvSelectHour = view.findViewById(R.id.tv_select_hour);
        edtNote = view.findViewById(R.id.edt_note);
        btnAdd = view.findViewById(R.id.btn_add);
        btnCancel = view.findViewById(R.id.btn_cancel);
        tvSelectDate.setOnClickListener(this);
        tvSelectHour.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        dialogSelectDate = new DialogSelectDate(getActivity());
        dialogSelectDate.setOnDataReceiveDateListener(this);
        dialogSelectHour = new DialogSelectHour(getActivity());
        dialogSelectHour.setOnReceiveDataTimePickerListener(this);

        datePicker = dialogSelectDate.findViewById(R.id.datePicker);
        timePicker = dialogSelectHour.findViewById(R.id.time_picker);
        
        initView();
        
        listView = view.findViewById(R.id.list_khamthai);
        loadListView();
    }

    private void loadListView() {
        ArrayList<LapLich> arrayList = databaseManager.getDataLapLich(Vas.TYPE_KHAMTHAI);
        adapter = new LapLichKhamThaiAdapter(arrayList);
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
            case R.id.tv_select_date:
                dialogSelectDate.show();
                break;
            case R.id.tv_select_hour:
                dialogSelectHour.show();
                break;
            case R.id.btn_add:
                checkInput();
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

    private void addDatabase() {
        String date = tvSelectDate.getText().toString();
        String hour = tvSelectHour.getText().toString();
        String note = edtNote.getText().toString();
        String type = Vas.TYPE_KHAMTHAI;
        LapLich lapLich = new LapLich(date,hour, note, type, true);
        if(databaseManager.insertDataLapLich(lapLich)){
            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
        }
        loadListView();
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

    @Override
    public void OnDataReceiveDate(boolean selectDate) {
        if(selectDate){
            String date = datePicker.getDayOfMonth() + "/" + (datePicker.getMonth() + 1) + "/" + datePicker.getYear();
            tvSelectDate.setText(date);
        }
    }

    @Override
    public void OnDataReceiveData(boolean selectHour) {
        int gio = timePicker.getCurrentHour();
        int phut = timePicker.getCurrentMinute();
        Calendar calendar = Calendar.getInstance();
        calendar.set(0,0,0,gio, phut);
        tvSelectHour.setText(gio + ":" + convertHour(phut));
    }

    private static String convertHour(int hour) {
        if (hour >= 10)
            return String.valueOf(hour);
        else
            return "0" + String.valueOf(hour);
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
