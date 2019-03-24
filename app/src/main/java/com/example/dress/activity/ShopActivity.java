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
import com.example.dress.util.Stamp.Stamp;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    //测试界面
    private RecyclerView recyclerView;

    List<Stamp> shopstamps = new ArrayList<>();
    private List<int[]> shopstampslist=new ArrayList<>();
    private ShopStampsAdapter shopStampsAdapter;

    private void initStamps(){
        int a[]={-1,-1};
        shopstampslist.clear();
        shopstamps.clear();
        shopstampslist.add(a);
        Stamp stamp1=new Stamp(R.drawable.ic_sync_black_24dp,1,1,0,10);
        Stamp stamp2=new Stamp(R.drawable.ic_info_black_24dp,1,2,0,10);
        Stamp stamp3=new Stamp(R.drawable.ic_notifications_black_24dp,2,1,0,10);
        shopstamps.add(stamp1);
        shopstamps.add(stamp2);
        shopstamps.add(stamp3);
        for(int i=0;i<shopstamps.size();i++){
            if(findGroup(shopstamps.get(i).getGroupIndex())!=-1){
               a[0]=shopstampslist.get(findGroup(shopstamps.get(i).getGroupIndex()))[0];
               a[1]=shopstampslist.get(findGroup(shopstamps.get(i).getGroupIndex()))[1]+1;
                //a[]={shopstampslist.get(findGroup(shopstamps.get(i).getGroupIndex()))[0],shopstampslist.get(findGroup(shopstamps.get(i).getGroupIndex()))[1]+1};
                shopstampslist.set(findGroup(shopstamps.get(i).getGroupIndex()),a);
            }else{
                a[0]=shopstampslist.get(findGroup(shopstamps.get(i).getGroupIndex()))[0];
                a[1]=1;
                shopstampslist.add(a);
            }
        }
    }
    private int findGroup(int key){
        for(int i=0;i<shopstampslist.size();i++){
            if(key==shopstampslist.get(i)[0]){
                return i;
            }
        }
        return -1;
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
