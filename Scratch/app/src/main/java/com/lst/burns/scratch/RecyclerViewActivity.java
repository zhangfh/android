package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//Use RecyclerView
//Step1 add compile 'com.android.support:recyclerview-v7:24.2.1'
//Step2 add recyclerView layout
//Step3 get RecycleView id and set layout manager
//Step4 set adapter

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private  MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_recyclerview);

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        mAdapter = new MyAdapter(getDummyDatas());
        mRecyclerView.setAdapter(mAdapter);
    }


    public  String[] getDummyDatas(){
        String[] data = {"this is data", "this is duck","this is end"};
        return data;
    }
    public   class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public String[] datas = null;

        public MyAdapter(String[] datas) {
            this.datas = datas;
        }
        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            System.out.println("onCreateViewHolder ");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item,parent,false);
            ViewHolder vh = new ViewHolder(view);
            return vh;


        }
        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            System.out.println("onBindViewHolder ");
            viewHolder.mTextView.setText(datas[position]);
        }
        //获取数据的数量
        @Override
        public int getItemCount() {
            System.out.println("getItemCount ");
            return datas.length;
        }
        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public  class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View view){
                super(view);
                mTextView = (TextView) view.findViewById(R.id.text);
            }
        }

    }
}