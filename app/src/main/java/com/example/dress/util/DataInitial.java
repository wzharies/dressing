package com.example.dress.util;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.dress.util.Api.ApiService;
import com.example.dress.util.Stamp.AllStamp;
import com.example.dress.util.Stamp.PerStamp;
import com.example.dress.util.Stamp.tempStamp;
import com.example.dress.util.jsondata.GetStampJson;
import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.jsondata.StampJson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DataInitial {

    public static void initDate() {
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

    public static int[][] convertCount(StampJson sj) {
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

    public static void initStamp() {
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

    public static AllStamp convertStamp(List<GetStampJson> data) {
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
                Log.i("convertStamp",tempStamp.toString());
            }else{
                tempStamp tempStamp = new tempStamp();
                tempStamp.setText(s.getText());
                tempStamp.setId(s.getId_2());
                tempStamp.setName(s.getName());
                PerStamp perStamp = perStamps.get(perStamps.size()-1);
                perStamp.getStamps().add(tempStamp);
                Log.i("convertStamp",tempStamp.toString());
            }
            // System.out.println(s.toString());
        }
        AllStamp allStamp = new AllStamp();
        allStamp.setPerStamps(perStamps);
        return allStamp;
    }
}
