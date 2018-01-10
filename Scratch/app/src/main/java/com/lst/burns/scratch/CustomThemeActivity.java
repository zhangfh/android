package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CustomThemeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.UAmpAppTheme);
        setContentView(R.layout.activity_customtheme);
    }
}