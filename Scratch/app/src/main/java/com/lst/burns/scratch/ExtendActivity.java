package com.lst.burns.scratch;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.lst.burns.scratch.Base.BaseActivity;

//Use BaseActivity
// BaseActivity is an abstract clas. must implement initView()
// Because of using toolbar, so must set no actionbar , here I use setTheme.
// override initActionBar to set toolbar.

public class ExtendActivity extends BaseActivity {
    private Toolbar mToolbar;


    @Override
    public void initView() {
        setTheme(R.style.UAmpAppTheme);
        setContentView(R.layout.activity_about);
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        // mToolbar.setLogo(R.mipmap.ic_launcher);
        mToolbar.setTitle("关于");// 标题的文字需在setSupportActionBar之前，不然会无效
        //mToolbar.setSubtitle("副标题");
        setSupportActionBar(mToolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        mToolbar.setNavigationIcon(R.drawable.ic_launcher);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ToolBar", "Click setNavigationIcon");
            }
        });
        //设置导航Icon和设置back冲突
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}