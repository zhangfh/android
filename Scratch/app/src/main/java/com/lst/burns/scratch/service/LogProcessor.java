package com.lst.burns.scratch.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import com.lst.burns.scratch.ILogProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class LogProcessor  extends Service {

    public static final int MSG_READ_FAIL = 1;
    public static final int MSG_LOG_FAIL = 2;
    public static final int MSG_NEW_LINE = 3;
    public static final int MSG_RESET_LOG = 4;
    public static final int MSG_LOG_SAVE = 5;

    public int MAX_LINES = 250;

    private volatile boolean threadKill = false;
    private volatile boolean mStatus = false;

    private String mBuffer = "main";
    private int mLines;
    private Vector<String> mScrollback;
    private int mType;
    private String mFilterTag;
    private String mFile;

    private static Handler mHandler;

    private final ILogProcessor.Stub mBinder = new ILogProcessor.Stub() {
        public void reset(String buffer) {
            requestKill();

            while (!mStatus) {
                try {
                    Log.d("Logger", "waiting...");
                } catch (Exception e) {
                    Log.d("Logger", "Woot! obj has been interrupted!");
                }
            }

            threadKill = false;
            mBuffer = buffer.toLowerCase();
            mLines = 0;
            mScrollback = null;
            mScrollback = new Vector<String>();
            Thread thr = new Thread(worker);
            thr.start();
        }

        public void run(int type) {
            mType = type;
            mLines = 0;
            mScrollback = new Vector<String>();
            Thread thr = new Thread(worker);
            thr.start();
        }

        public void restart(int type) {
            requestKill();

            while(!mStatus) {
                try {
                    Log.d("Logger", "waiting...");
                } catch (Exception e) {
                    Log.d("Logger", "Woot! we have an exception");
                }
            }

            threadKill = false;
            run(type);
        }

        public void stop() {
            Log.i("Logger", "stop() method called in service.");
            requestKill();
            stopSelf();
        }

        public void write(String file, String tag) {
            mFilterTag = tag;
            mFile = file;
            Thread thr = new Thread(writer);
            thr.start();
        }

    };
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public IBinder onBind(Intent intent) {
        return mBinder;

    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Logger","onUnbind");
        requestKill();
        stopSelf();

        return false;
    }

    Runnable worker = new Runnable() {
        public void run() {
            runLog();
            mStatus = true;
            Log.d("Logger", "status... " + mStatus);//when it will be call?
            return;
        }
    };
    Runnable writer = new Runnable() {
        public void run() {
            writeLog();
            return;
        }
    };

    private synchronized void requestKill() {
        threadKill = true;
    }
    private synchronized boolean killRequested() {
        return threadKill;
    }
    private void communicate(int msg) {
        Message.obtain(mHandler, msg, "error").sendToTarget();
    }
    private void logLine(String line) {
        Message.obtain(mHandler, MSG_NEW_LINE, line).sendToTarget();
    }
    private void runLog() {
        Process process = null;

        try {

            if (mType == 0) {
                process = Runtime.getRuntime().exec("/system/bin/logcat -b " + mBuffer); //-b set logcat buffer type
            } else if (mType == 1) {
                process = Runtime.getRuntime().exec("dmesg -s 1000000");//set kernel buffer
            }

        } catch (IOException e) {
            communicate(MSG_LOG_FAIL);
        }

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            Log.e("Logger","start read logcat killRequested " + killRequested());
            while (!killRequested()) {
                line = reader.readLine();

                logLine(line);

                if (mLines == MAX_LINES) {
                    mScrollback.removeElementAt(0);
                }

                mScrollback.add(line);

                mLines++;
            }

            Log.i("Logger", "Prepping thread for termination");
            reader.close();
            process.destroy();
            process = null;
            reader = null;
            mScrollback.removeAllElements();
            mScrollback = null;
            mLines = 0;
        } catch (IOException e) {
            communicate(MSG_READ_FAIL);
        }

        Log.d("Logger", "Exiting thread...");
        return;
    }
    private void writeLog() {

        try {
            File f = new File("/sdcard/" + mFile);
            FileWriter w = new FileWriter(f);

            for (int i = 0; i < mScrollback.size(); i++) {
                String line = mScrollback.elementAt(i);

                if (!mFilterTag.equals("")) {
                    String tag = line.substring(2, line.indexOf("("));

                    if (mFilterTag.toLowerCase().equals(tag.toLowerCase().trim())) {
                        w.write(line + "\n");
                    }
                } else {
                    w.write(mScrollback.elementAt(i) + "\n");
                }

                i++;
            }

            if (!mFile.equals("tmp.log")) {
                Message.obtain(mHandler, MSG_LOG_SAVE, "saved").sendToTarget();
            } else {
                Message.obtain(mHandler, MSG_LOG_SAVE, "attachment").sendToTarget();
            }

            w.close();
            f = null;
        } catch (Exception e) {
            Log.e("Logger", "Error writing the log to a file. Exception: " + e.toString());
            Message.obtain(mHandler, MSG_LOG_SAVE, "error").sendToTarget();
        }

        return;
    }
    public static void setHandler(Handler handler) {
        mHandler = handler;
    }

}