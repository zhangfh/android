package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.lst.burns.scratch.Object.Item;
import com.lst.burns.scratch.Object.ItemDecoration;
import com.lst.burns.scratch.Object.TimeLineAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDecorationActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Item> mList = new ArrayList<>();
    TimeLineAdapter mAdapter;
    int[] resId = {
            R.drawable.image_1,
//            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4,
            R.drawable.image_5,
            R.drawable.image_6,
            R.drawable.image_7,
            R.drawable.image_8
    };
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_timeline1);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_timeline1);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.addItemDecoration(new ItemDecoration(this,100));

        for (int i = 0; i < 7; i++) {
            Item item = new Item();
            item.setResId(resId[i]);
            item.setText("我从未见过如此厚颜无耻之人");
            mList.add(item);
        }
        mAdapter = new TimeLineAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
    }
}