package com.lst.burns.scratch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lst.burns.scratch.Object.Student;

public class ShowMessageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton_bind_data_in_intent;
    private Button mButton_bind_data_in_bundle;
    private Button mButton_bind_parcel_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmessagebetweenactivity);

        mButton_bind_data_in_intent = (Button)findViewById(R.id.bind_data_in_intent);
        mButton_bind_data_in_intent.setOnClickListener(this);

        mButton_bind_data_in_bundle = (Button)findViewById(R.id.bind_data_in_bundel);
        mButton_bind_data_in_bundle.setOnClickListener(this);

        mButton_bind_parcel_data = (Button)findViewById(R.id.bind_parcel_data);
        mButton_bind_parcel_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i = null;
        switch (view.getId()){
            case R.id.bind_data_in_intent:
                i = new Intent(this, GetDataFromActivity.class);
                i.putExtra("name","this is data in intent");
                break;
            case R.id.bind_data_in_bundel:
                i = new Intent(this, GetDataFromActivity.class);
                Bundle b = new Bundle();
                b.putString("name", "this is data in bundle");
                i.putExtra("personBdl", b);
                break;
            case R.id.bind_parcel_data:
                i = new Intent(this, GetDataFromActivity.class);
                Student p = new Student();
                p.setName("name1");
                p.setSurname("smith");
                p.setEmail("new@1.com");
                i.putExtra("myStudent", p);

                break;
            default:
                break;
        }
        startActivity(i);
    }
}