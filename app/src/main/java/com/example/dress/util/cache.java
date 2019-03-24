package com.example.dress.util;

import android.database.sqlite.SQLiteDatabase;

import com.example.dress.util.Letter.AllLetter;
import com.example.dress.util.Stamp.AllStamp;

import org.litepal.LitePal;

public class cache {
    private static AllLetter letters=null;
    private static User user=null;
    private static AllStamp allStamp=null;


    public static void getCache(){
        SQLiteDatabase db = LitePal.getDatabase();
        letters = LitePal.findFirst(AllLetter.class);
        user = LitePal.findFirst(User.class);
    }

    public static User getUser(){
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
}
