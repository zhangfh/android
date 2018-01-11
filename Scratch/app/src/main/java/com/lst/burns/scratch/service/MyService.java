package com.lst.burns.scratch.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    @Override
    public IBinder onBind(Intent arg0){
        Log.i("ZFH","MyService onBind");
        return null;
    }
    @Override
    public void onCreate(){
        Log.i("ZFH","MyService onCreate");
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("ZFH","MyService onStartCommand");
        Log.i("ZFH","MyService get intent is: " + intent.getStringExtra("name"));
        return  super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        Log.i("ZFH","MyService onDestroy");
        super.onDestroy();
    }
}