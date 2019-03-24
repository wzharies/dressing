package com.example.dress.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dress.R;
import com.example.dress.adapter.StampAdapter;
import com.example.dress.util.Stamp.AllStamp;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.cache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempStampViewActivity extends BaseActivity {
    @BindView(R.id.stamp_recycleView) RecyclerView recyclerView;
    List<PerStamp> stamps = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_view);
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        stamps = getStamps(cache.getAllStamp());
        StampAdapter stampAdapter = new StampAdapter(stamps);
        recyclerView.setAdapter(stampAdapter);

        //设置toolbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private List<PerStamp> getStamps(AllStamp allStamp){
        for(PerStamp stamp:allStamp.getPerStamps()){
            if(stamp.getHave()!=0){
                stamps.add(stamp);
            }
        }
        return stamps;
    }


}
