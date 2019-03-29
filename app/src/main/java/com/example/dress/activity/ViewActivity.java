package com.example.dress.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;

import com.example.dress.R;
import com.example.dress.activity.fragement.Fragment1;
import com.example.dress.activity.fragement.Fragment2;
import com.example.dress.activity.fragement.Fragment3;
import com.example.dress.adapter.BottomnavigationViewPagerAdapter;
import com.example.dress.util.Api.ApiService;
import com.example.dress.util.RetrofitManager;
import com.example.dress.util.Stamp.AllStamp;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.Stamp.tempStamp;
import com.example.dress.util.cache;
import com.example.dress.util.jsondata.GetStampJson;
import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.jsondata.StampJson;
import com.google.gson.JsonObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        initDate();
        initStamp();
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

    private void initDate() {
        JsonObject json = new JsonObject();
        json.addProperty("id", cache.getUser().getId());
        RetrofitManager.create(ApiService.class).getAllStampCount(cache.getUser().getToken(), json)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<StampJson>>() {
                    @Override
                    public void accept(@NonNull ResponseData<StampJson> rs) throws Exception {
                        if (rs == null) {
                            Log.e("stampcount", "连接不到服务器");
                        } else if (rs.getRet() == 0) {
                            cache.setStampcount(convertCount(rs.getData()));
                            cache.setMoney(rs.getData().getMoney());
                            Log.e("stampcount", "邮票数量初始化完毕");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("register", throwable.getMessage());
                    }
                });
    }

    private int[][] convertCount(StampJson sj) {
        String[] count = sj.getCount().split(";");
        int[][] c = new int[count.length][];
        for (int i = 0; i < count.length; i++) {
            String[] count2 = count[i].split(",");
            int[] c2 = new int[count2.length];
            for (int j = 0; j < count2.length; j++) {
                c2[j] = Integer.parseInt(count2[j]);
//                System.out.print(c2[j]);
            }
            c[i] = c2;
 //           System.out.println("-");
        }
        return c;
    }

    private void initStamp() {
        RetrofitManager.create(ApiService.class).getAllStamp(cache.getUser().getToken())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<List<GetStampJson>>>() {
                    @Override
                    public void accept(@NonNull ResponseData<List<GetStampJson>> rs) throws Exception {
                        if (rs == null) {
                            Log.e("stampcount", "连接不到服务器");
                        } else if (rs.getRet() == 0) {
                            cache.setAllStamp(convertStamp(rs.getData()));
                            Log.e("stampcount", "邮票数量初始化完毕");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("register", throwable.getMessage());
                    }
                });
    }

    private AllStamp convertStamp(List<GetStampJson> data) {
        Collections.sort(data);
        List<PerStamp> perStamps = new ArrayList<>();
        for (GetStampJson s : data) {
            if (s.getId_2() == 0) {
                PerStamp perStamp = new PerStamp();
                perStamp.setId(s.getId_1());
                tempStamp tempStamp = new tempStamp();
                tempStamp.setText(s.getText());
                tempStamp.setId(s.getId_2());
                tempStamp.setName(s.getName());
                List<tempStamp> tempStamps = new ArrayList<>();
                tempStamps.add(tempStamp);
                perStamp.setStamps(tempStamps);
                perStamps.add(perStamp);
                //System.out.println(tempStamp.toString());
            }else{
                tempStamp tempStamp = new tempStamp();
                tempStamp.setText(s.getText());
                tempStamp.setId(s.getId_2());
                tempStamp.setName(s.getName());
                PerStamp perStamp = perStamps.get(perStamps.size()-1);
                perStamp.getStamps().add(tempStamp);
                //System.out.println(tempStamp.toString());
            }
           // System.out.println(s.toString());
        }
        AllStamp allStamp = new AllStamp();
        allStamp.setPerStamps(perStamps);
        return allStamp;
    }
}
