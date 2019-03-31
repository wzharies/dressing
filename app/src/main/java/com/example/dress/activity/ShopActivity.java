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

    private void initStamps(){
        //int a[][]=new int[100][3];
        shopstampslist.clear();

       /* shopstamps.clear();
       // shopstampslist.add(a);
        Stamp stamp1=new Stamp(R.drawable.ic_sync_black_24dp,1,1,0,10);
        Stamp stamp2=new Stamp(R.drawable.ic_info_black_24dp,1,2,0,10);
        Stamp stamp3=new Stamp(R.drawable.ic_notifications_black_24dp,2,1,0,10);
        shopstamps.add(stamp1);
        shopstamps.add(stamp2);
        shopstamps.add(stamp3);
        for(int i=0;i<shopstamps.size();i++){
            if(a[shopstamps.get(i).getGroupIndex()][0]==shopstamps.get(i).getGroupIndex()){
               a[shopstamps.get(i).getGroupIndex()][1]=a[shopstamps.get(i).getGroupIndex()][1]+1;
                a[shopstamps.get(i).getGroupIndex()][2]=a[shopstamps.get(i).getGroupIndex()][2]+shopstamps.get(i).getMoney();

            }else{
                a[shopstamps.get(i).getGroupIndex()][0]=shopstamps.get(i).getGroupIndex();
                a[shopstamps.get(i).getGroupIndex()][1]=1;
                a[shopstamps.get(i).getGroupIndex()][2]=shopstamps.get(i).getMoney();
                shopstampslist.add(a[shopstamps.get(i).getGroupIndex()]);
            }
        }*/
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
