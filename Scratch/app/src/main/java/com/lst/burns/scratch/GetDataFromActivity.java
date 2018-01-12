package com.lst.burns.scratch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.lst.burns.scratch.Object.Student;

public class GetDataFromActivity extends AppCompatActivity {

    private TextView mTextView_from_intent;
    private TextView mTextView_from_bundle;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_getdatafromanother);

        Intent i =  getIntent();

        String name_intent = i.getStringExtra("name");

        Bundle b = i.getBundleExtra("personBdl");


        mTextView_from_intent = (TextView)findViewById(R.id.get_data_from_intent);
        mTextView_from_bundle = (TextView)findViewById(R.id.get_data_from_bundle);

        if (name_intent != null)
            mTextView_from_intent.setText(name_intent);
        if (b != null){
            String name_bundle = b.getString("name");
            if (name_bundle !=null)
                mTextView_from_bundle.setText(name_bundle);
        }


        Bundle b1 = i.getExtras();

        if(b1 != null) {
            Student p = (Student) b1.getParcelable("myStudent");
            if (p != null) {
                String name = p.getName();
                String surname = p.getSurname();
                String email = p.getEmail();
                Log.i("ZFH", "name: " + name + " surname " + surname + " email " + email);
            }
        }



    }
}