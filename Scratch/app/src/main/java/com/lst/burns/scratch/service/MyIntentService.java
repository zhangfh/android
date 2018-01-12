package com.lst.burns.scratch.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {

        super("MyIntentService");
        Log.d("ZFH","MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("ZFH","onHandleIntent");
        if(intent != null){
            String name = intent.getStringExtra("name");

           Log.d("ZFH","current thread name " + Thread.currentThread().getName());
            Log.d("ZFH","name " + name);
        }

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.e("ZFH","onDestroy");
    }
}