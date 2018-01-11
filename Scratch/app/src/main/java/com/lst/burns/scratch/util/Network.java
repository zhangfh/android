package com.lst.burns.scratch.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

//get wifi connectioninfo need permission: <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
public class Network {
    public static String getLocalIp(Context context)
    {
        WifiManager wifiManager = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);//获取WifiManager

        //检查wifi是否开启 emulator has no wifi.
        if (!wifiManager.isWifiEnabled())
        {
            return null;
        }

        WifiInfo wifiinfo = wifiManager.getConnectionInfo();

        String ip = intToIp(wifiinfo.getIpAddress());

        return ip;
    }

    private static String intToIp(int paramInt)
    {
        return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "."
                + (0xFF & paramInt >> 16) + "." + (0xFF & paramInt >> 24);
    }
}