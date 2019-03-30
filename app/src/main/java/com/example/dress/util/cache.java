package com.example.dress.util;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dress.util.Letter.AllLetter;
import com.example.dress.util.Stamp.AllStamp;

import org.litepal.LitePal;

import java.util.ArrayList;

public class cache {
    private static AllLetter letters=null;
    private static User user=null;
    private static AllStamp allStamp=null;
    private static int[][] stampcount=null;
    private static ArrayList<Integer> whichhava;
    private static int money=-1;

    public static void getCache(){
        SQLiteDatabase db = LitePal.getDatabase();
        letters = LitePal.findFirst(AllLetter.class);
        user = LitePal.findFirst(User.class);
    }

    public static User getUser(){
        User user=new User("22323","jjflying",56,"4555");
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
        if(allStamp==null){
            DataInitial.initStamp();
        }
        return allStamp;
    }

    public static void setAllStamp(AllStamp allStamp) {
        cache.allStamp = allStamp;
    }

    public static int[][] getStampcount() {
        if(stampcount==null){
            DataInitial.initDate();
        }
        return stampcount;
    }

    public static void setStampcount(int[][] stampcount) {
        cache.stampcount = stampcount;
        initwhichhave();
    }

    public static int getMoney() {
        if(money==-1){
            DataInitial.initDate();
        }
        return money;
    }

    public static void setMoney(int money) {
        cache.money = money;
    }

    public static ArrayList<Integer> getWhichhava() {
        if(whichhava==null){
            initwhichhave();
        }
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
