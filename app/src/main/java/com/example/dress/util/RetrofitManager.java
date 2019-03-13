package com.example.dress.util;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    public final static String url = "http://10.0.2.2:8080/";
    public static OkHttpClient mOkHttpClirnt;
    public static OkHttpClient getOkHttpClient(){
        mOkHttpClirnt =  new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)//连接超时时间
                .readTimeout(10, TimeUnit.SECONDS)//读取超时时间
                .writeTimeout(10,TimeUnit.SECONDS)//写入超时时间
                .build();
        return mOkHttpClirnt;
    }

    public static<T> T create(Class<T> clazz){
        Retrofit retrofit =new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}
