package com.example.dress.util;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.LinearLayout;

import org.litepal.LitePal;

public class cache {
    private static AllLetter letters=null;
    private static User user=null;


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
}
