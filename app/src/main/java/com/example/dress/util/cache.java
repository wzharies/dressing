package com.example.dress.util;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.dress.activity.LoginActivity;
import com.example.dress.activity.RegisterActivity;
import com.example.dress.util.Api.ApiService;
import com.example.dress.util.Letter.AllLetter;
import com.example.dress.util.Letter.Letter;
import com.example.dress.util.Stamp.AllStamp;
import com.example.dress.util.jsondata.ResponseData;
import com.example.dress.util.jsondata.StampJson;
import com.google.gson.JsonObject;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class cache {
    private static AllLetter letters=null;
    private static User user=null;
    private static AllStamp allStamp=null;
    private static int[][] stampcount=null;
    private static ArrayList<Integer> whichhava;
    private static int money;

    public static void getCache(){
        //SQLiteDatabase db = LitePal.getDatabase();
        //letters = LitePal.findFirst(AllLetter.class);
        //user = LitePal.findFirst(User.class);
    }

    public static User getUser(){
        User user = new User("186666666","shit",1,"11111");
       return user;
    }
    public static void setUser(User ur){
        user = ur;
    }

    public static AllLetter getLetters() {
        return letters;
    }

    public static void setLetters(AllLetter letters) {
        cache.letters = letters;
    }

    public static AllStamp getAllStamp() {
        return allStamp;
    }

    public static void setAllStamp(AllStamp allStamp) {
        cache.allStamp = allStamp;
    }

    public static int[][] getStampcount() {

        return stampcount;
    }

    public static void setStampcount(int[][] stampcount) {
        cache.stampcount = stampcount;
        initwhichhave();
    }

    public static int getMoney() {
        return money;
    }

    public static void setMoney(int money) {
        cache.money = money;
    }

    public static ArrayList<Integer> getWhichhava() {
        return whichhava;
    }

    public static void setWhichhava(ArrayList<Integer> whichhava) {
        cache.whichhava = whichhava;
    }

    public static void initwhichhave(){
        whichhava = new ArrayList<>();
        for(int i =0;i<stampcount.length;i++){
            for(int j=0;j<stampcount[i].length;j++){
                //System.out.println(stampcount[i][j]);
                if(stampcount[i][j]!=0){
                    whichhava.add(i);
                    Log.i("whichhava",i+"-");
                    break;
                }
            }
        }

    }
}
