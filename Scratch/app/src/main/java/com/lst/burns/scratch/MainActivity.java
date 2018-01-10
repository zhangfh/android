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
            case R.id.addtoolbox:
                i = new Intent(this,AddToolBox.class);
            case R.id.adddrawer:
                i = new Intent(this,AddDrawerLayout.class);
            default:
                break;
        }
        startActivity(i);
    }
}
