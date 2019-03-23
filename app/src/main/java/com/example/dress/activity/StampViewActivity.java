package com.example.dress.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dress.R;
import com.example.dress.adapter.StampAdapter;
import com.example.dress.util.Stamp;

import java.util.ArrayList;
import java.util.List;

public class StampViewActivity extends AppCompatActivity {

    List<Stamp> stamps = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp_view);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.stamp_recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置内容
        initStampList();
        StampAdapter stampAdapter = new StampAdapter(stamps);
        recyclerView.setAdapter(stampAdapter);

        //设置toolbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void initStampList(){
        Stamp temp1 = new Stamp(R.drawable.text_notyet,1,0,1,10);
        Stamp temp2 = new Stamp(R.drawable.text_notyet,1,0,1,10);
        Stamp temp3 = new Stamp(R.drawable.text_notyet,1,0,1,10);
        temp1.setText("0/9");
        temp2.setText("0/9");
        stamps.add(temp1);stamps.add(temp2);stamps.add(temp3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select_stamp_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.stamp_select_next:
                Intent intent = new Intent(this, WtringActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                finish();
                break;


        }
        return true;
    }
}
