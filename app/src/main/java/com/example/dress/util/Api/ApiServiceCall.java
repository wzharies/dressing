package com.example.dress.util.Api;

import com.example.dress.util.Letter.Letter;
import com.example.dress.util.jsondata.ResponseData;
import com.google.gson.JsonObject;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiServiceCall {
    @POST("getAllLetter")
    Call<ResponseData<List<Letter>>> getAllLetter(@Header(value="token")String token, @Body JsonObject json);

}
