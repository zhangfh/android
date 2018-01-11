package com.lst.burns.scratch.Base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.lst.burns.scratch.R;

public abstract class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();


    }
    //R.id.fragmentContainer
    public  void initFragment(int resid){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(resid);

        if (fragment == null){
            fragment = createFragment();
            fm.beginTransaction().add(resid,fragment).commit();

        }
    }
    protected abstract void initView();
    protected abstract  Fragment createFragment();



}