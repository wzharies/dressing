package com.example.dress.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dress.R;
import com.example.dress.adapter.More_StampAdapter;
import com.example.dress.adapter.StampAdapter;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.Stamp.tempStamp;
import com.example.dress.util.cache;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class More_StampActivity extends BaseActivity {
    @BindView(R.id.stamp_recycleView)
    RecyclerView recyclerView;
    List<tempStamp> stamps=null;
    int[] stampcount;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_view);
        ButterKnife.bind(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        Intent intent = getIntent();
        int position = intent.getIntExtra("stamp",0);
        Letter letter =(Letter)intent.getSerializableExtra("letter");
        More_StampAdapter stampAdapter = new More_StampAdapter(position,letter);
        recyclerView.setAdapter(stampAdapter);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
