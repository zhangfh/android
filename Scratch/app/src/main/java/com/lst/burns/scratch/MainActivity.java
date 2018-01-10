package com.lst.burns.scratch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button mButton_CustomTheme;
    private Button mButton_CustomTheme2;
    private Button mButton_AddToolbox;
    private Button mButton_DrawerLayout;
    private Button mButton_CardView;
    private Button mButton_Fragment;

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
            default:
                break;
        }
        startActivity(i);
    }
}
