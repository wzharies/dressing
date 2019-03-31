package com.example.dress.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.example.dress.R;
import com.example.dress.activity.fragement.Fragment1;
import com.example.dress.activity.fragement.Fragment2;
import com.example.dress.activity.fragement.Fragment3;
import com.example.dress.adapter.BottomnavigationViewPagerAdapter;
import com.example.dress.util.DataInitial;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewActivity extends BaseActivity {
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.bottomNavigationView) BottomNavigationView bottomNavigationView;
    @BindView(R.id.mainBar) Toolbar toolbar;
    String[] strings = new String[]{"探索","信箱","我的"};
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    List<Fragment> fragmentList;
    BottomnavigationViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);

       // setSupportActionBar(replyToolbar);
        //replyToolbar.setTitle("写信");
        /*
        ActionBar actionBar = getSupportActionBar();

        if(actionBar!=null)
            actionBar.setDisplayHomeAsUpEnabled(true);
            */
        setSupportActionBar(toolbar);
        init();
        DataInitial.initDate();
        DataInitial.initStamp();
    }

    private void init() {
        fragmentList = new ArrayList<>();
        fragment1 = Fragment1.newInstance();
        fragment2 = Fragment2.newInstance();
        fragment3 = Fragment3.newInstance();
        if (!fragmentList.contains(fragment1)) {
            fragmentList.add(fragment1);
        }
        if (!fragmentList.contains(fragment2)) {
            fragmentList.add(fragment2);
        }
        if (!fragmentList.contains(fragment3)) {
            fragmentList.add(fragment3);
        }

        pagerAdapter = new BottomnavigationViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bottomNavigationView.getMenu().getItem(i).setChecked(true);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle(strings[i]);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        viewPager.setCurrentItem(1);

        bottomNavigationView.setSelectedItemId(R.id.item_2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(strings[1]);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        changePageFragment(item.getItemId());
                switch (item.getItemId()) {
                    case R.id.item_1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.item_2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.item_3:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.view_send_letter:
                showListDialog();
                break;
        }
        return true;
    }

    public void showDatePickerDialog(Activity activity, int themeResId, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity
                ,  themeResId
                // 绑定监听器(How the parent is notified that the date is set.)
                ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                System.out.println("您选择了：" + (year) + "年" + monthOfYear+1
                        + "月" + dayOfMonth + "日");
                writingletter2(year,monthOfYear+1,dayOfMonth);
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                ,calendar.get(Calendar.MONTH)
                ,calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    private void showListDialog() {
        final String[] items = { "写给自己的信","写给陌生人的信"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(ViewActivity.this);
        listDialog.setTitle("请选择发送书信的类型：");
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    showNormalDialog();
                }else if(which==1){
                    writingletter1();
                }
            }
        });
        listDialog.show();
    }

    private void showNormalDialog(){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final android.app.AlertDialog.Builder normalDialog =
                new android.app.AlertDialog.Builder(ViewActivity.this);
        //  normalDialog.setIcon(R.drawable.icon_dialog);
        normalDialog.setTitle("");
        normalDialog.setMessage("请选择未来收到信的时间");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showDatePickerDialog(ViewActivity.this,5,Calendar.getInstance());
                    }
                });
        normalDialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    private void writingletter1(){
        Intent intent = new Intent(ViewActivity.this, WtringActivity.class);
        intent.putExtra("type",1);
        startActivity(intent);
    }


    private void writingletter2(int year,int month,int day){
        Intent intent = new Intent(ViewActivity.this, WtringActivity.class);
        intent.putExtra("type",2);

        intent.putExtra("year",year);
        intent.putExtra("month",month);
        intent.putExtra("day",day);
        startActivity(intent);
        /*
        Intent intent = new Intent(this, WtringActivity.class);
        intent.putExtra("type",0);
        intent.putExtra("id",envelope.getSenderid());
        intent.putExtra("receiver",envelope.getSender());
        intent.putExtra("sender",envelope.getReceiver());
        startActivity(intent);
        */
    }

}
