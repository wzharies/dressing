package com.example.dress.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.example.dress.R;
import com.example.dress.activity.fragement.Fragment1;
import com.example.dress.activity.fragement.Fragment2;
import com.example.dress.activity.fragement.Fragment3;
import com.example.dress.adapter.BottomnavigationViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewActivity extends BaseActivity {
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;


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
        init();
    }

    protected void init(){
        fragmentList = new ArrayList<>();
        fragment1 = Fragment1.newInstance();
        fragment2 = Fragment2.newInstance();
        fragment3 = Fragment3.newInstance();
        if(!fragmentList.contains(fragment1)){
            fragmentList.add(fragment1);
        }
        if(!fragmentList.contains(fragment2)){
            fragmentList.add(fragment2);
        }
        if(!fragmentList.contains(fragment3)){
            fragmentList.add(fragment3);
        }

        pagerAdapter = new BottomnavigationViewPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bottomNavigationView.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bottomNavigationView.setSelectedItemId(R.id.item_1);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        changePageFragment(item.getItemId());
                switch (item.getItemId()){
                    case  R.id.item_1:
                        viewPager.setCurrentItem(0);
                        break;
                    case  R.id.item_2:
                        viewPager.setCurrentItem(1);
                        break;
                    case  R.id.item_3:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }
}
