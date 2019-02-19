package com.example.dress.util;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.jsondata.User;
import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HttpConnection {
    public static ResponseData<User> rdlogin = null;
    public static ResponseData<Object> rdregister = null;

    public static ResponseData<User> login(String name, String password){

        JsonObject jsonuser = new JsonObject();
        jsonuser.addProperty("username",name);
        jsonuser.addProperty("password",password);

        RetrofitManager.create(ApiService.class).getUser(jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<User>>() {
                    @Override
                    public void accept(@NonNull ResponseData<User> responseData) throws Exception {
                        rdlogin = responseData;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("login",throwable.getMessage());
                    }
                });
        return rdlogin;
    }

    public static ResponseData register(String name,String password){
        final ResponseData<User> rd;

        JsonObject jsonuser = new JsonObject();
        jsonuser.addProperty("username",name);
        jsonuser.addProperty("password",password);
        RetrofitManager.create(ApiService.class).register(jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<Object>>() {
                    @Override
                    public void accept(@NonNull ResponseData<Object> responseData) throws Exception {
                        rdregister = responseData;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e("register",throwable.getMessage());
                    }
                });
        return rdregister;
    }
}
