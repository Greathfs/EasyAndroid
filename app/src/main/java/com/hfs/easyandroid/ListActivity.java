package com.hfs.easyandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * @author HuangFusheng
 * @date 2018/12/12
 * @description ListActivity
 */
public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecycleView;

    private PhotoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecycleView=findViewById(R.id.recycleview_id);
        mAdapter=new PhotoAdapter(this);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.setAdapter(mAdapter);

        mAdapter.setList(PhotoBean.createInstances(new DataUrls().getUrls()));

        mAdapter.notifyDataSetChanged();
    }
}
