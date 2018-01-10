package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//Step1 : add dependence in build.gradle: compile 'com.android.support:cardview-v7:24.2.1'
//Step2 : add new layout activity_cardview
public class AddCardView extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);
    }
}