package com.lst.burns.scratch.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.lst.burns.scratch.http.RequestListenerThread;
import com.lst.burns.scratch.util.Constant;

//
//socket failed: EACCES (Permission denied) <uses-permission android:name="android.permission.INTERNET" />
//Create Thread RequestListenerThread to monitor
//Use FileBrowseHandler as HttpRequestHandler.
//If there is a request ,use WorkThread to handle, WorkThread will call httpService->handleRequest,
//Actually, it will call FileBrowseHandler->handle
//I cannot use pc to access emulator 10.0.2.15:7890, but I use emulator's browser to access 127.0.0.1:7890, the browser crash :)
public class WebService extends Service {

    private RequestListenerThread thread;
    private Context context;

    public WebService() {
        Log.d("ZFH","WebService");
    }

    @Override
    public void onCreate() {
        Log.d("ZFH","WEbservice create");
        super.onCreate();

        context = getApplicationContext();
        thread = new RequestListenerThread(Constant.Config.PORT, Constant.Config.Web_Root, context);
        thread.setDaemon(false);
        thread.start();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        new Thread() {
            public void run() {
                if (thread != null)
                    thread.destroy();
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}