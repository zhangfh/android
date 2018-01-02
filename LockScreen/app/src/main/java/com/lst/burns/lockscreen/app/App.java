package com.lst.burns.lockscreen.app;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.lst.burns.lockscreen.service.LockerService;
import com.lst.burns.lockscreen.util.KeyGuardUtil;

public class App extends Application {
    static LockerService.ViewServiceBinder binder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ZFH","App onCreate");
        bindLockerService();
        KeyGuardUtil.init(this);

    }


    private void bindLockerService() {
        Intent intent = new Intent(this, LockerService.class);
        bindService(intent, new LockServiceConnection(), Context.BIND_AUTO_CREATE);
    }

    private class LockServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("ZFH","onServiceConnected");
            binder = (LockerService.ViewServiceBinder) service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public static void lock() {
        KeyGuardUtil.getInstance().disableKeyGuard();
        binder.getService().showView();
    }

    public static void unlock() {
        binder.getService().removeView();
    }


}