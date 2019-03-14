package com.example.dress.activity;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;


public class Myapplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

    public static Context getContext(){
        return context;
    }
}
