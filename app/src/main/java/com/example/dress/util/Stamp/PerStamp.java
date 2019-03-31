package com.example.dress.util.Stamp;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class PerStamp  extends LitePalSupport implements Serializable {
    private int id;
    private  int value;
    private List<tempStamp> stamps;

    public void setValu(int value){this.value=value;}

    public int getValue(){return value;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<tempStamp> getStamps() {
        return stamps;
    }

    public void setStamps(List<tempStamp> stamps) {
        this.stamps = stamps;
    }
}
