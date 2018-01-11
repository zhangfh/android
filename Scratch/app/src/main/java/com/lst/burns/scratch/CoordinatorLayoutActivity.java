package com.lst.burns.scratch;
//Use CoordinatorLayout
//Step1: compile 'com.android.support:design:24.1.0'
//Step2: create activity_coordinator.xml
        //1.add FloatingActionButton and add click listener.
        //compile error: replace compile 'com.android.support:design:24.2.1', rebuild
        //2. add toolbar and tabbar.
            //set theme with no actionbar.
            //setSupportActionBar
            //setDisplayHomeAsUpEnabled
            //override onOptionsItemSelected to finish back function

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class CoordinatorLayoutActivity extends AppCompatActivity {

    FloatingActionButton fabBtn;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.UAmpAppTheme);
        setContentView(R.layout.activity_coordinator);

        rootLayout = (CoordinatorLayout)findViewById(R.id.rootLayout);
        fabBtn = (FloatingActionButton) findViewById(R.id.fab);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Hello. I am Snackbar!", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .show();
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //android.R.id.home对应应用程序图标的id
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}