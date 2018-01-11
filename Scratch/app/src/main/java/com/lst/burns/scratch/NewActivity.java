package com.lst.burns.scratch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
//Use action to start new activity.
//define action in manifes file. Use Intent(String) to start activity.
//define android:process to create a new process.
public class NewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton_inprocess;
    private Button mButton_newprocess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newprocess);
        mButton_inprocess = (Button)findViewById(R.id.button_start_in_process);
        mButton_newprocess = (Button)findViewById(R.id.button_start_in_new_process);

        mButton_inprocess.setOnClickListener(this);
        mButton_newprocess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = null;
        switch (view.getId()){
            case  R.id.button_start_in_process:
                i = new Intent("com.lst.burns.scratch.subactivity.in.process");
                break;
            case R.id.button_start_in_new_process:
                i = new Intent("com.lst.burns.scratch.subactivity.in.new.process");
                break;

        }
        startActivity(i);

    }
}