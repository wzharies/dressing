package com.example.dress.util;

import java.util.Calendar;


public class Timestamp {

    public static String gettime(){
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int date = c.get(Calendar.DATE);
        return year+"-"+month+"-"+date;
    }
}
