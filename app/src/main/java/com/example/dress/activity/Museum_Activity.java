package com.example.dress.activity;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dress.R;

public class Museum_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView toolBarImage;
    private RecyclerView recyclerView;
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
                    Toast.makeText(Museum_Activity.this,"折叠了",Toast.LENGTH_SHORT).show();
                }

                else if(expendedtag==2&&i==0){
                    //展开监听
                    Toast.makeText(Museum_Activity.this,"展开了",Toast.LENGTH_SHORT).show();
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

            }
        });
        //第二个分类
        tag_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //第三个分类
        tag_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    private void initView(){
        toolbar = (Toolbar)findViewById(R.id.envelope_toolbar);
        appBarLayout=(AppBarLayout)findViewById(R.id.appBarlayout);
        collapsingToolbar=(CollapsingToolbarLayout)findViewById(R.id.collasping_toolbBar);
        toolBarImage = (ImageView)findViewById(R.id.toolbar_museum_image);
        //设置分类
        tag_1=findViewById(R.id.classfy_tag1);
        tag_2=findViewById(R.id.classfy_tag2);
        tag_3=findViewById(R.id.classfy_tag3);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        collapsingToolbar.setTitle(" ");
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


}
