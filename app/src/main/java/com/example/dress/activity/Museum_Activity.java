package com.example.dress.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dress.R;
import com.example.dress.adapter.ShowLetterAdapter;
import com.example.dress.util.ShowLetter.AllShowLetter;
import com.example.dress.util.ShowLetter.ShowLetterGroup;
import com.example.dress.util.ShowLetter.TempShowLetter;
import com.example.dress.util.cache;

import java.util.ArrayList;
import java.util.List;

public class Museum_Activity extends AppCompatActivity  {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView toolBarImage;
    //recycleview的相关变量
    int tag=0;
    private RecyclerView recyclerView;
    private ShowLetterAdapter adapter;
    private AllShowLetter allShowLetter= cache.getAllShowLetter();
    private List<TempShowLetter> showLetters = new ArrayList<>();

//分类部分
    TextView classfyText;
    private Button tag_1;
    private Button tag_2;
    private Button tag_3;

    //因为setExpanded会调用事件监听，所以通过标志过滤掉
    //监听toolbar的折叠与展开
    public static int expendedtag=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_);
        initView();


        //下面都是监听事件
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if(getSupportActionBar().getHeight()-appBarLayout.getHeight()==i){
                    //折叠监听
                    toolBarImage.setVisibility(View.VISIBLE);
                    collapsingToolbar.setTitle("信件博物馆");
                    //Toast.makeText(Museum_Activity.this,"折叠了",Toast.LENGTH_SHORT).show();
                }

                else if(expendedtag==2&&i==0){
                    //展开监听
                    //Toast.makeText(Museum_Activity.this,"展开了",Toast.LENGTH_SHORT).show();
                    collapsingToolbar.setTitle(" ");
                }
                else{
                    collapsingToolbar.setTitle(" ");
                    toolBarImage.setVisibility(View.INVISIBLE);
                }
                if(expendedtag!=2&&i==0){
                    expendedtag++;
                }

            }
        });
        //第一个分类
        tag_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag!=1) {
                    recyclerView.removeAllViews();
                    tag = 1;
                    initShowLetterList();
                }
            }
        });
        //第二个分类
        tag_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag!=2) {
                    recyclerView.removeAllViews();
                    tag = 2;
                    initShowLetterList();
                }
            }
        });
        //第三个分类
        tag_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tag!=3) {
                    recyclerView.removeAllViews();
                    tag = 3;
                    initShowLetterList();
                }
            }
        });


    }
    //顶部导航栏的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //初始化所有图像
    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.envelope_toolbar);
        appBarLayout=(AppBarLayout)findViewById(R.id.appBarlayout);
        collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collasping_toolbBar);
        toolBarImage = (ImageView)findViewById(R.id.toolbar_museum_image);


        recyclerView = (RecyclerView)findViewById(R.id.museum_recycleView);
        //设置分类
        classfyText = findViewById(R.id.museum_classfy_text);
        tag_1=findViewById(R.id.classfy_tag1);
        tag_2=findViewById(R.id.classfy_tag2);
        tag_3=findViewById(R.id.classfy_tag3);
        classfyText.setVisibility(View.VISIBLE);
        tag_1.setVisibility(View.VISIBLE);
        tag_2.setVisibility(View.VISIBLE);
        tag_3.setVisibility(View.VISIBLE);


        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        collapsingToolbar.setTitle(" ");
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //设置recycleview的布局
        initShowLetterList();
    }
    //初始化展示信件列表
    private void initShowLetterList(){
        showLetters.clear();
        List<ShowLetterGroup> groups=allShowLetter.getShowLetterGroups();
        if(tag==0){
            ShowLetterGroup group1 = groups.get(0);
            ShowLetterGroup group2 = groups.get(1);
            ShowLetterGroup group3 = groups.get(2);
            for(int i=0;i<2;i++) {
                showLetters.add(group1.getShowLetters().get(i));
                showLetters.add(group2.getShowLetters().get(i));
                showLetters.add(group3.getShowLetters().get(i));
            }


        }else if(tag==1){
            //设置其他的颜色
            tag_1.setActivated(true);
            tag_2.setActivated(false);
            tag_3.setActivated(false);
            ShowLetterGroup group1 = groups.get(0);
            for(int i=0;i<group1.getShowLetters().size();i++) {
                showLetters.add(group1.getShowLetters().get(i));

            }

        }else if(tag==2){
            tag_1.setActivated(false);
            tag_2.setActivated(true);
            tag_3.setActivated(false);
            ShowLetterGroup group2 = groups.get(1);
            for(int i=0;i<group2.getShowLetters().size();i++) {
                showLetters.add(group2.getShowLetters().get(i));
            }

        }else  if(tag==3){
            tag_1.setActivated(false);
            tag_2.setActivated(false);
            tag_3.setActivated(true);
            ShowLetterGroup group3 = groups.get(2);
            for(int i=0;i<group3.getShowLetters().size();i++) {
                showLetters.add(group3.getShowLetters().get(i));

            }
        }
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new ShowLetterAdapter(showLetters);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
