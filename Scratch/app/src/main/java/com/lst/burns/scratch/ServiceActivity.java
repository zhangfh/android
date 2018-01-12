package com.lst.burns.scratch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lst.burns.scratch.service.ConvertService;
import com.lst.burns.scratch.service.MyIntentService;
import com.lst.burns.scratch.service.MyService;

//common service : MyService
//ServiceIntent:  MyIntentService
//communication by messenger and service: ConvertService.

//Messenger关联一个可以发送消息的Handler
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton_Start_Intent_Service;
    private Button mButton_Stop_Intent_Service;
    private Button mButton_Start_Service;
    private Button mButton_Stop_Service;
    private Button mButton_Send_Message_To_Service;

    private ServiceConnection sConn;
    private ServiceConnection mServiceConnection;
    private Messenger messenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        mButton_Start_Intent_Service = (Button)findViewById(R.id.start_intent_service);
        mButton_Start_Intent_Service.setOnClickListener(this);
        mButton_Stop_Intent_Service = (Button)findViewById(R.id.stop_intent_service);
        mButton_Stop_Intent_Service.setOnClickListener(this);

        mButton_Start_Service = (Button)findViewById(R.id.start__service);
        mButton_Start_Service.setOnClickListener(this);

        mButton_Stop_Service = (Button)findViewById(R.id.stop__service);
        mButton_Stop_Service.setOnClickListener(this);

        mButton_Send_Message_To_Service = (Button)findViewById(R.id.sendMessageToService);
        mButton_Send_Message_To_Service.setOnClickListener(this);

        sConn = new ServiceConnection(){
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("ZFH","onServiceDisconnected");
            }
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // We are conntected to the service
                Log.i("ZFH","onServiceConnected");

            }
        };

        mServiceConnection = new ServiceConnection(){
            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d("ZFH","onServiceDisconnected ConvertService");
                messenger = null;
            }
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // We are conntected to the service
                Log.i("ZFH","onServiceConnected ConvertService");
                messenger = new Messenger(service);
            }
        };

        // We bind to the service
        bindService(new Intent(this, ConvertService.class), mServiceConnection,
                Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View view) {
        Intent i = null;
        switch(view.getId()){
            case R.id.start_intent_service:
                i = new Intent(this, MyIntentService.class);
                i.putExtra("name", "SurvivingwithAndroid");
                startService(i);
                break;
            case R.id.stop_intent_service:
                i = new Intent(this, MyIntentService.class);
                stopService(i);
                break;
            case R.id.start__service:
                i = new Intent(this, MyService.class);
                i.putExtra("name", "SurvivingwithAndroid");

                bindService(new Intent(this, MyService.class), sConn,
                        Context.BIND_AUTO_CREATE);
                startService(i);
                break;
            case R.id.stop__service:
                i = new Intent(this, MyService.class);
                unbindService(sConn);
                stopService(i);
                break;
            case R.id.sendMessageToService:
                String val = "this is client message";
                Message msg = Message
                        .obtain(null, ConvertService.TO_UPPER_CASE);

                msg.replyTo = new Messenger(new ResponseHandler());
                // We pass the value
                Bundle b = new Bundle();
                b.putString("data", val);

                msg.setData(b);

                try {
                    messenger.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }

    // This class handles the Service response
    class ResponseHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case ConvertService.TO_UPPER_CASE_RESPONSE: {
                    String result = msg.getData().getString("respData");
                    Log.d("ZFH","get message from service: " + result);

                }
            }
        }

    }
}