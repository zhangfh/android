package com.lst.burns.mesureboottime;

import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

public class DeviceHelper {

    static {
        System.loadLibrary("panel");
    }


    /**
     * get android system boot time(unit milli second).
     * time since 1970-01-01 00:00:00 +0000 (UTC).
     */
    public static long getBootTime() {
        if (Build.VERSION.SDK_INT < 17) {
            Log.d("ZFH","version less 17");
            return  0;
            //return native_getBootTime() * 1000;
        }
        return native_getBootTime() * 1000;
        //return System.currentTimeMillis() - SystemClock.elapsedRealtimeNanos() / 1000000;
    }

    /**
     * get android system boot time(unit second).
     * time since 1970-01-01 00:00:00 +0000 (UTC).
     */
    public static native long native_getBootTime();
}