package com.duongton.camnangbabau.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Theson on 11/30/2017.
 */

public class MyAlarmService extends IntentService {

    public MyAlarmService() {
        super("MyTestService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //Do the task here
        Log.i("MyTestService", "Servicio ejecutandose. Recordatorios");
    }
}
