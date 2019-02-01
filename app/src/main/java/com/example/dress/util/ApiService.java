package com.example.dress.util;

import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.jsondata.User;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Observable<ResponseData> getUser(@Body JsonObject json);

    @POST("register")
    Observable<ResponseData> register(@Body JsonObject json);

}
