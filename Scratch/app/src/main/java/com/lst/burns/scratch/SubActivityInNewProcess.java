package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SubActivityInNewProcess extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub);

    }
}