package com.lst.burns.scratch;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private static final String ACTION_DISMISSED_WARNING = "PNW.dismissedWarning";
    private static final String ACTION_SHOW_BATTERY_SETTINGS = "PNW.batterySettings";

    private Button mButton_CustomTheme;
    private Button mButton_CustomTheme2;
    private Button mButton_AddToolbox;
    private Button mButton_DrawerLayout;
    private Button mButton_CardView;
    private Button mButton_Fragment;
    private Button mButton_NewActivity;
    private Button mButton_Coordinator;
    private Button mButton_UseBaseActivity;
    private Button mButton_UseNotification;
    private Button mButton_StartHttpServer;
    private Button mButton_ServiceTest;
    private Button mButton_AddBaseFragment;
    private Button mButton_ShowMessageBetweenActivity;
    private Button mButton_AddListView;
    private Button mButton_AddAnimation;
    private Button mButton_AddGridView;
    private Button mButton_Counter;
    private Button mButton_Log;
    private Button mButton_HandlerThread;
    private Button mButton_RecyclerView;
    private Button mButton_PostOffice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton_CustomTheme = (Button)findViewById(R.id.customtheme);
        mButton_CustomTheme.setOnClickListener(this);

        mButton_CustomTheme2 = (Button)findViewById(R.id.customtheme2);
        mButton_CustomTheme2.setOnClickListener(this);

        mButton_AddToolbox = (Button)findViewById(R.id.addtoolbox);
        mButton_AddToolbox.setOnClickListener(this);

        mButton_DrawerLayout= (Button)findViewById(R.id.adddrawer);
        mButton_DrawerLayout.setOnClickListener(this);

        mButton_CardView = (Button)findViewById(R.id.addcardview);
        mButton_CardView.setOnClickListener(this);

        mButton_Fragment = (Button)findViewById(R.id.addfragment);
        mButton_Fragment.setOnClickListener(this);

        mButton_NewActivity = (Button)findViewById(R.id.newactivity);
        mButton_NewActivity.setOnClickListener(this);

        mButton_Coordinator = (Button)findViewById(R.id.usecoordinatory);
        mButton_Coordinator.setOnClickListener(this);

        mButton_UseBaseActivity = (Button)findViewById(R.id.usebaseactivity);
        mButton_UseBaseActivity.setOnClickListener(this);

        mButton_UseNotification = (Button)findViewById(R.id.usenotification);
        mButton_UseNotification.setOnClickListener(this);

        mButton_StartHttpServer = (Button)findViewById(R.id.starthttpserver);
        mButton_StartHttpServer.setOnClickListener(this);

        mButton_ServiceTest = (Button)findViewById(R.id.servicetest);
        mButton_ServiceTest.setOnClickListener(this);

        mButton_AddBaseFragment = (Button)findViewById(R.id.addbasefragment);
        mButton_AddBaseFragment.setOnClickListener(this);

        mButton_ShowMessageBetweenActivity = (Button)findViewById(R.id.sendextratoactivity);
        mButton_ShowMessageBetweenActivity.setOnClickListener(this);

        mButton_AddListView = (Button)findViewById(R.id.add_listview);
        mButton_AddListView.setOnClickListener(this);

        mButton_AddAnimation = (Button)findViewById(R.id.add_animation);
        mButton_AddAnimation.setOnClickListener(this);

        mButton_AddGridView = (Button)findViewById(R.id.add_gridview);
        mButton_AddGridView.setOnClickListener(this);

        mButton_Counter = (Button)findViewById(R.id.counter_activity);
        mButton_Counter.setOnClickListener(this);


        mButton_Log = (Button)findViewById(R.id.log_activity);
        mButton_Log.setOnClickListener(this);

        mButton_HandlerThread = (Button)findViewById(R.id.handler_thread);
        mButton_HandlerThread.setOnClickListener(this);

        mButton_RecyclerView = (Button)findViewById(R.id.recycler_view);
        mButton_RecyclerView.setOnClickListener(this);

        mButton_PostOffice = (Button)findViewById(R.id.post_office);
        mButton_PostOffice.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = null;
        switch (view.getId())
        {
            case R.id.customtheme:
                i= new Intent(this, CustomThemeActivity.class);
                break;
            case R.id.customtheme2:
                i = new Intent(this,CustomThemeActivity2.class);
                break;
            case R.id.addtoolbox:
                i = new Intent(this,AddToolBox.class);
                break;
            case R.id.adddrawer:
                i = new Intent(this,AddDrawerLayout.class);
                break;
            case R.id.addcardview:
                i = new Intent(this,AddCardView.class);
                break;
            case R.id.addfragment:
                i = new Intent(this,AddFragment.class);
                break;
            case R.id.newactivity:
                i = new Intent(this,NewActivity.class);
                break;
            case R.id.usecoordinatory:
                i = new Intent(this,CoordinatorLayoutActivity.class);
                break;
            case R.id.usebaseactivity:
                i = new Intent(this,ExtendActivity.class);
                break;
            case R.id.usenotification:
                //this is a special case;
                startNotification();
                return;
            case R.id.starthttpserver:
                i = new Intent(this,HttpServerActivity.class);
                break;
            case R.id.servicetest:
                i = new Intent(this,ServiceActivity.class);
                break;
            case R.id.addbasefragment:
                i = new Intent(this,FragmentActivity.class);
                break;
            case R.id.sendextratoactivity:
                i = new Intent(this, ShowMessageActivity.class);
                break;
            case R.id.add_listview:
                i = new Intent(this, ListViewActivity.class);
                break;
            case R.id.add_animation:
                i = new Intent(this, AddAnimationActivity.class);

                break;
            case R.id.add_gridview:
                i = new Intent(this, GridViewActivity.class);
                break;
            case R.id.counter_activity:
                i = new Intent(this,CounterActivity.class);
                break;
            case R.id.log_activity:
                i = new Intent(this, LogActivity.class);
                break;
            case R.id.handler_thread:
                i = new Intent(this, HandlerThreadActivity.class);
                break;
            case R.id.recycler_view:
                i = new Intent(this, RecyclerViewActivity.class);
                break;
            case R.id.post_office:
                i = new Intent(this,PostOfficeActivity.class);
            default:
                break;
        }
        startActivity(i);
    }

    private void startNotification(){
        final Notification.Builder nb = new Notification.Builder(MainActivity.this)
                .setSmallIcon(R.drawable.ic_launcher)
                // Bump the notification when the bucket dropped.
                .setWhen(System.currentTimeMillis())
                .setShowWhen(false)
                .setContentTitle("This is notification")
                .setContentText("5%")
                .setOnlyAlertOnce(true)
                .setDeleteIntent(pendingBroadcast(ACTION_DISMISSED_WARNING))
                .setPriority(Notification.PRIORITY_MAX)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setColor(MainActivity.this.getResources().getColor(R.color.battery_saver_mode_color));

        nb.setContentIntent(pendingBroadcast(ACTION_SHOW_BATTERY_SETTINGS));
        final Notification n = nb.build();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0x123, n);
    }
    private PendingIntent pendingBroadcast(String action) {
        Log.d("ZFH","pendingBroadcast");
        return PendingIntent.getBroadcast(this,0,new Intent(action),0);

    }
}
