package com.example.dress.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dress.R;
import com.example.dress.adapter.StampAdapter;
import com.example.dress.util.Stamp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class StampViewActivity extends AppCompatActivity {

    int[][] stampList=new int[11][11];              //邮票信息的二维数组

    List<Stamp> stamps = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        int tag=intent.getIntExtra("tag",-1);
        int group_index=intent.getIntExtra("group_index",0);

        setContentView(R.layout.activity_stamp_view);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.stamp_recycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        StampAdapter stampAdapter=null;
        //设置内容
        //其中分为两种加载方法
        if(tag==0) {                            //如果是0就加载所有组的内容
            initStampList_0();
            stampAdapter = new StampAdapter(stamps,0);
        }
        else {
            initStampList_1(group_index);
            stampAdapter = new StampAdapter(stamps,1);
        }//如果不是就加载下标所在那个组组里面的所有邮票



        recyclerView.setAdapter(stampAdapter);

        //设置toolbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    private void initStampList_0(){
        getStampMecessage();
        Stamp[] temp=new Stamp[8];
        int i,k;
        for(i=1;i<=7;i++) {
            int count=0,sum=9;
            temp[i] = new Stamp(R.drawable.text_notyet, 1, 0, 1);
            for(k=1;k<=9;k++){
                if(stampList[i][k]>0){
                    count++;
                    temp[i]=new Stamp(getResource(i, k), i, k, 1);
                    temp[i].setText(String.valueOf(count)+"/"+String.valueOf(sum));
                }
            }

        }
        for(i=1;i<=7;i++)
        {
            stamps.add(temp[i]);
        }

    }

    private void initStampList_1(int group){
        getStampMecessage();
        Stamp[] temp=new Stamp[10];
        int i,k;
        for(i=1;i<10;i++){
            temp[i] = new Stamp(R.drawable.text_notyet, 0, 0, 0);
            if(stampList[group][i]>0) {
                temp[i] = new Stamp(getResource(group, i), group, i, stampList[group][i]);
                temp[i].setText(String.valueOf(stampList[group][i]));
            }
        }

        for(i=1;i<=9;i++)
        {
            stamps.add(temp[i]);
        }
    }

    //获取邮票二维数组的信息
    private void getStampMecessage(){
        stampList[3][3]=1;
        stampList[3][8]=2;
        stampList[6][3]=1;
        stampList[2][2]=7;
        stampList[1][1]=1;
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
    /*
    另一只种获取id的方法
    public int getImageId(int group,int index){
        String s="stamp"+String.valueOf(group)+"_"+String.valueOf(index)+".png";
        Log.i("Imageid",s);
        Context ctx=getBaseContext();
        int resId = getResources().getIdentifier(s,"darwable",ctx.getPackageName());
        return resId;
    }
    */

    //通过反射机制获取id
    public int  getResource(int group,int index){
        String imageName="stamp"+String.valueOf(group)+"_"+String.valueOf(index);
        Class drawble = R.drawable.class;
        Log.i("Imageid",imageName);
        try {
            Field field = drawble.getField(imageName);
            int resId = field.getInt(imageName);
            return resId;
        } catch (NoSuchFieldException e) {//如果没有在"mipmap"下找到imageName,将会返回0
            return 0;
        } catch (IllegalAccessException e) {
            return 0;
        }

    }
}
