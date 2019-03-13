package com.example.dress.util;

import java.io.Serializable;
import java.util.Calendar;

public class Token implements Serializable {
    private String tokenid;

/*
    public boolean isValid(){
        long currenttime;
        currenttime = Calendar.getInstance().getTimeInMillis() / 1000;
        if(currenttime-time<validtime){
            return true;
        }
        return false;
    }*/

    public String getTokenid() {
        return tokenid;
    }

    public void setTokenid(String tokenid) {
        this.tokenid = tokenid;
    }
}
