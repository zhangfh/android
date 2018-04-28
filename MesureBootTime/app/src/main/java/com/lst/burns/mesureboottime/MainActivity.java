package com.lst.burns.mesureboottime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    /*
    static {
        System.loadLibrary("panel");
    }
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tv = (TextView) findViewById(R.id.time_text);
        tv.setText(String.valueOf(DeviceHelper.getBootTime()) + "ms");
    }
}
