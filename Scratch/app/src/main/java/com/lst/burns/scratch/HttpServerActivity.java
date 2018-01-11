package com.lst.burns.scratch;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lst.burns.scratch.service.WebService;
import com.lst.burns.scratch.util.Constant;
import com.lst.burns.scratch.util.Network;

public class HttpServerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView_Ip;
    private Button mButton_StartServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpserver);
        mTextView_Ip = (TextView)findViewById(R.id.ipaddress);
        String ip = Network.getLocalIp(this);//emulator get null
        mTextView_Ip.setText(ip);
        Log.d("ZFH","Constant mime " + Constant.theMimeTypes);

        Log.d("ZFH","packagename " + this.getPackageName());

        try {
            ApplicationInfo path = this.getPackageManager().getApplicationInfo(this.getPackageName(), 0);
            Log.d("ZFH","sourcedir " + path.sourceDir);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        mButton_StartServer = (Button)findViewById(R.id.start_http_server);
        mButton_StartServer.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_http_server:
                Intent serviceIntent = new Intent(this, WebService.class);
                startService(serviceIntent);
                break;
            default:
                break;
        }
    }
}