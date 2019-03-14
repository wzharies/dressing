package com.example.dress.util;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.litepal.LitePal;

public class cache {
    private static User user=null;
    public static void getCache(){
        SQLiteDatabase db = LitePal.getDatabase();
        user = LitePal.findFirst(User.class);
        Log.i("cache",user.toString());
    }

    public static User getUser(){
        return user;
    }
    public static void setUser(User ur){
        user = ur;
    }
}
