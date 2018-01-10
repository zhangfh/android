package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AddToolBox extends AppCompatActivity {

    //Step1  add dependence in build.gradle: compile 'com.android.support:design:24.1.0'
    //Step2  set theme No ActionBar, Here I use API setTheme to finish.
    //Step3  add new layout, add android.support.v7.widget.Toolbar , see activity_toolbar.xml
    //Step4  using this layout
    //Step5. setSupportActionBar
    //because Toolbar use android:layout_height="match_parent", so toolbar occupy the whole screen.
    //     android:layout_height="wrap_content" android:minHeight="?attr/actionBarSize" to make toolbar occupy actionbar position
    //Now toolbar can show, but other view cannot below toolbar.
    //FiX: add  android:layout_below="@+id/toolbar" in other view.
    //Step6. setLogo
    //Step7. setTitle
    //Step8. setSubtitle
    //Step9 setNavigationIcon
    //Step10 setNavigationOnClickListener

    //Basically, menu will be added into toolbox
    //Step1: create menu_main.xml
    //Step2: override onCreateOptionsMenu , inflate menu_main.xml
    //Step3: implement Toolbar OnMenuItemClickListener interface onMenuItemClickListener
    //Step4: Toolbar   setOnMenuItemClickListener
    private Toolbar mToolbar;

    private Toolbar.OnMenuItemClickListener onMenuItemClickListener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){

                case R.id.action_play:
                    Snackbar.make(mToolbar,"Click Share",Snackbar.LENGTH_SHORT).show();
                    break;
                case R.id.action_stop:
                    Snackbar.make(mToolbar,"Click More",Snackbar.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //step1 设置主题为No ActionBar
        setTheme(R.style.UAmpAppTheme);
        setContentView(R.layout.activity_toolbar);

        initializeToolbar();
    }
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        Log.d("ZFH","setTitle " + title);
    }
    protected void initializeToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new IllegalStateException("Layout is required to include a Toolbar with id " +
                    "'toolbar'");
        }
        setSupportActionBar(mToolbar);

        //app logo
        mToolbar.setLogo(R.drawable.ic_launcher);
        //title
        mToolbar.setTitle("  Material Design ToolBar");
        mToolbar.setSubtitle("ToolBar subtitle");

        mToolbar.setNavigationIcon(R.drawable.ic_allmusic_black_24dp);

        //添加菜单点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mToolbar, "Click setNavigationIcon", Snackbar.LENGTH_SHORT).show();
            }
        });
        //set menu click
        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}