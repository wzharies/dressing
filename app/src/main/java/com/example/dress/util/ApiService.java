package com.example.dress.util;

import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Observable<ResponseData<Map<String,Object>>> getUser(@Body JsonObject json);

    @POST("register")
    Observable<ResponseData<Object>> register(@Body JsonObject json);

}
