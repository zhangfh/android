package com.lst.burns.iconcis;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.Iconics;
import com.mikepenz.iconics.context.IconicsContextWrapper;
import com.mikepenz.iconics.context.IconicsLayoutInflater;

import com.mikepenz.iconics.view.IconicsButton;
import com.mikepenz.iconics.view.IconicsImageView;
import com.mikepenz.weather_icons_typeface_library.WeatherIcons;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      // LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
       //LayoutInflaterCompat.setFactory2(getLayoutInflater(), new IconicsLayoutInflater2(getDelegate()));

        Iconics.init(getApplicationContext());
        //Iconics.registerFont(new GoogleMaterial());
       // Iconics.registerFont(new WeatherIcons());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



}
