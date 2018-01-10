package com.lst.burns.scratch;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class AddDrawerLayout extends AppCompatActivity {

    //Step1   Drawer always use toolbox. so copy all code from AddToolBox.
    //Step2   use activity_drawer as layout.
    //DrawerLayout最好为界面的根布局
    //设置了layout_gravity="start/left"的视图才会被认为是侧滑菜单
    //        Note: please place toolbox in FrameLayout
    //Now Drawer is working, using mouse slide from left to right.
    //Step3  create property mDrawerLayout and mDrawerToggle
    //Step4 Bind ActionBarDrawerToggle with Toolbar
    //Step5 create DrawerListener
    //Note: after use Drawer setNavigationOnClickListener is useless. It seems bind Toolbar  see ActionBarDrawerToggle constructor.

    //Use NavigationView as sub view of Drawer.
    //Step1. create layout nav_header as NavigationView's headerLayout
    //Step2. create drawer.xml in menu as NavigationView's menu
    //Step3  use NavigationView see activity_drawer.xml
    //Step4 setNavigationItemSelectedListener -- when clicked, close drawer, save item id in mItemToOpenWhenDrawerCloses
    //      then deal with in onDrawerClosed
    //Step5 syncState will replace NavigationIcon
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private int mItemToOpenWhenDrawerCloses = -1;

    private final DrawerLayout.DrawerListener mDrawerListener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerClosed(View drawerView) {
           // Log.d("ZFH","onDrawerClosed");
            if (mDrawerToggle != null) mDrawerToggle.onDrawerClosed(drawerView);
            //deal with item in drawer that is clicked.
            switch (mItemToOpenWhenDrawerCloses) {
                case R.id.navigation_allmusic:
                    Log.d("ZFH","item navigation_allmusic clicked");
                    break;
                case R.id.navigation_playlists:
                    Log.d("ZFH","item navigation_playlists clicked");
                    break;
            }

        }

        @Override
        public void onDrawerStateChanged(int newState) {
           // Log.d("ZFH","onDrawerStateChanged newState " +  newState);
            if (mDrawerToggle != null) mDrawerToggle.onDrawerStateChanged(newState);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
           // Log.d("ZFH","onDrawerSlide slideOffset " +  slideOffset);
            if (mDrawerToggle != null) mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
           // Log.d("ZFH","onDrawerOpened  " );
            if (mDrawerToggle != null) mDrawerToggle.onDrawerOpened(drawerView);
            if (getSupportActionBar() != null)
                getSupportActionBar().setTitle("TEST");
        }
    };
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
        setContentView(R.layout.activity_drawer);

        initializeToolbar();
        initDrawer();
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
                Log.d("ZFH","setNavigationOnClickListener");
                Snackbar.make(mToolbar, "Click setNavigationIcon", Snackbar.LENGTH_SHORT).show();
            }
        });
        //set menu click
        mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }



    protected void initDrawer(){
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Create an ActionBarDrawerToggle that will handle opening/closing of the drawer:
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                mToolbar, R.string.open_content_drawer, R.string.close_content_drawer);
        mDrawerLayout.setDrawerListener(mDrawerListener);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView == null) {
            throw new IllegalStateException("Layout requires a NavigationView " +
                    "with id 'nav_view'");
        }
        populateDrawerItems(navigationView);
        updateDrawerToggle();
    }


    private void populateDrawerItems(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mItemToOpenWhenDrawerCloses = menuItem.getItemId();
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    protected void updateDrawerToggle() {
        if (mDrawerToggle == null) {
            return;
        }

       // mDrawerToggle.setDrawerIndicatorEnabled(true);
        /*
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
        */
        if (true) {
            mDrawerToggle.syncState();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}