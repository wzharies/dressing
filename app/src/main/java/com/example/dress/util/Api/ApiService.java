package com.example.dress.util.Api;

import com.example.dress.util.Letter.Letter;
import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {

    @POST("login")
    Observable<ResponseData<Map<String,Object>>> getUser(@Body JsonObject json);

    @POST("register")
    Observable<ResponseData<Object>> register(@Body JsonObject json);

    @POST("updateinfo")
    Observable<ResponseData<Map<String,Object>>> updateinfo(@Header(value ="token")String token ,@Body JsonObject json);

    @POST("writingletter")
    Observable<ResponseData<Object>> writingletter(@Header(value="token")String token,@Body JsonObject json);

    @POST("getAllLetter")
    Observable<ResponseData<List<Letter>>> getAllLetter(@Header(value="token")String token, @Body JsonObject json);

}
