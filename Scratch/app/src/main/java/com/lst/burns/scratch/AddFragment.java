package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//Step1: load layout activity_fragment.
//   Note: activity_fragment include a fragment, class is SingleFragment.
public class AddFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}