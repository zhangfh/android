package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.lst.burns.scratch.Object.DtsFile;
import com.lst.burns.scratch.Object.StringList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DtsParserActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_dtsparser);
        mRecyclerView = (RecyclerView) findViewById(R.id.dts_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        InputStream im = getResources().openRawResource(R.raw.skeleton);

        BufferedReader read = new BufferedReader(new InputStreamReader(im));

        String line = "";
        StringBuilder sb = new StringBuilder();
        try {
            while((line = read.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(read != null) {
                try {
                    read.close();
                    read = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(im != null) {
                try {
                    im.close();
                    im = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i("ZFH","get string: " + sb.toString());
        DtsFile dts = new DtsFile(sb.toString());
        dts.Strip();
        dts.findRootCompatible();


        StringList sl = new StringList("a-string-list-property = \"first string\", \"second string\";");
        sl.IsStringList();
        sl.Parse();
    }
}