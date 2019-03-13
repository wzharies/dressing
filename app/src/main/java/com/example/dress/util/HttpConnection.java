package com.example.dress.util;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.dress.activity.ViewActivity;
import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HttpConnection {
    public static ResponseData<Map<String,Object>> rdlogin = null;
    public static ResponseData<Object> rdregister = null;

    public static ResponseData<Map<String,Object>> login(String name, String password) {

        JsonObject jsonuser = new JsonObject();
        jsonuser.addProperty("phone",name);
        jsonuser.addProperty("password",password);

        RetrofitManager.create(ApiService.class).getUser(jsonuser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseData<Map<String,Object>>>(){
                    @Override
                    public void accept(@NonNull ResponseData<Map<String,Object>> responseData) throws Exception {
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

    public static ResponseData<Object> register(String phone,String password1){
            JsonObject jsonuser = new JsonObject();
            jsonuser.addProperty("phone",phone);
            jsonuser.addProperty("password",password1);
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
