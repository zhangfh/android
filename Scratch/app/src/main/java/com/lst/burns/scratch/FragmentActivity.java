package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

import com.lst.burns.scratch.Base.BaseFragmentActivity;

//  super.onCreate --> initView --> initFragment --> createFragment
public class FragmentActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        initFragment(R.id.fragmentContainer);//must call this function
    }
    @Override
    protected void initView() {
        setContentView(R.layout.activity_fragment_base);
    }

    @Override
    protected Fragment createFragment() {
        return new RemoteControlFragment();
    }
}