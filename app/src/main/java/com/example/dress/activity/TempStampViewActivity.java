package com.example.dress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dress.R;
import com.example.dress.adapter.StampAdapter;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.Stamp.AllStamp;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.cache;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TempStampViewActivity extends BaseActivity {
    @BindView(R.id.stamp_recycleView) RecyclerView recyclerView;
    List<PerStamp> stamps=null;
    int[][] stampcount;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_view);
        Intent intent = getIntent();
        Letter letter = (Letter)intent.getSerializableExtra("letter");
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        StampAdapter stampAdapter = new StampAdapter(cache.getAllStamp().getPerStamps(),cache.getStampcount(),letter);
        recyclerView.setAdapter(stampAdapter);

        //设置toolbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    /*
    private List<PerStamp> getStamps(AllStamp allStamp){
        List<PerStamp> ans = new ArrayList<>();
        List<PerStamp> perStamps = allStamp.getPerStamps();
        for(int i = 0;i<perStamps.size();i++){
            for(int j = 0;j<stampcount[i].length;j++){
                if(stampcount[i][j]!=0){
                    ans.add(perStamps.get(i));
                }
            }
        }
        return stamps;
    }

    private int[][] getStampCount(int[][] stampcount){
        ArrayList<Integer> cnt = cache.getWhichhava();
        int[][] ans = new int[cnt.size()][];
        for(int i =0;i<cnt.size();i++){
            for(int j = 0;j<stampcount[cnt.get(i)].length;j++){
                ans[i] = stampcount[cnt.get(i)];
            }
        }
        return ans;
    }
    */

}
