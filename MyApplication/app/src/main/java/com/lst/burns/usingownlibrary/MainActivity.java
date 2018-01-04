package com.lst.burns.usingownlibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lst.burns.opensourcelibrary.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtils.showToast(this,"welcome");
    }
}
