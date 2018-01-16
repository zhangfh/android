package com.lst.burns.scratch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lst.burns.scratch.Interface.ICounterCallback;
import com.lst.burns.scratch.Interface.ICounterService;
import com.lst.burns.scratch.service.CounterService;

public class CounterActivity extends AppCompatActivity implements View.OnClickListener , ICounterCallback {

    private final static String TAG = "burns.counter.Activity";

    private Button startButton = null;
    private Button stopButton = null;
    private TextView counterText = null;

    private ICounterService counterService = null;


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            counterService = ((CounterService.CounterBinder)iBinder).getService();
            Log.i(TAG,"Counter Service connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            counterService = null;
            Log.i(TAG,"Counter Srvice disconnected");
        }
    };
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_counter);
        startButton = (Button)findViewById(R.id.button_start);
        startButton.setOnClickListener(this);

        stopButton = (Button)findViewById(R.id.button_stop);
        stopButton.setOnClickListener(this);

        counterText = (TextView)findViewById(R.id.textView_counter);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);

        Intent bindIntent = new Intent(CounterActivity.this,CounterService.class);
        bindService(bindIntent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_start:
                if(counterService !=null){
                    counterService.startCounter(0,this);

                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);

                }
                break;
            case R.id.button_stop:
                if(counterService != null){
                    counterService.stopCounter();

                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void count(int val) {
        String text = String.valueOf(val);
        counterText.setText(text);
    }
}