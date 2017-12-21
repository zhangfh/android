package com.lst.burns.recyclerviewwithfastadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.adapters.ItemAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


        private static final String[] ALPHABET = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            //fill with some sample data
            int x = 0;
            List<SimpleItem> items = new ArrayList<>();
            for (String s : ALPHABET) {
                int count = new Random().nextInt(20);
                for (int i = 1; i <= count; i++) {
                    SimpleItem item = new SimpleItem();
                    item.name = s + "T " + i ;
                    item.description = s + "D" + i;
                    items.add(item);
                    x++;
                }
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardListView);
        //create the ItemAdapter holding your Items
        ItemAdapter itemAdapter = new ItemAdapter();
        //create the managing FastAdapter, by passing in the itemAdapter
        FastAdapter fastAdapter = FastAdapter.with(itemAdapter);




        //set the items to your ItemAdapter
        itemAdapter.add(items);
            LinearLayoutManager llm = new LinearLayoutManager(this);
            llm.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(llm);

            //set our adapters to the RecyclerView
            recyclerView.setAdapter(fastAdapter);


    }
}
