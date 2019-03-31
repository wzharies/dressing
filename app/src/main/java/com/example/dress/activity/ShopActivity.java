package com.example.dress.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.adapter.ShopStampsAdapter;
import com.example.dress.util.Stamp.PerStamp;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    //测试界面
    private RecyclerView recyclerView;


    private List<PerStamp> shopstampslist=new ArrayList<>();
    private ShopStampsAdapter shopStampsAdapter;

    //把从数据库得到的perstamp加入到shopstamplist里
    private void initStamps(){

        shopstampslist.clear();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Toolbar toolbar=(Toolbar)findViewById(R.id.shop_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            Log.d("a","b");
            actionBar.setDisplayShowHomeEnabled(true);

        }

        initStamps();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.shop_recyclerview);
        GridLayoutManager layoutmanager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutmanager);
        shopStampsAdapter=new ShopStampsAdapter(shopstampslist);
        recyclerView.setAdapter(shopStampsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.shop,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.shop_addmony:
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                finish();
                break;
                default:
        }
        return true;
    }
}
