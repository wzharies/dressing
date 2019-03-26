package com.example.dress.util.Stamp;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class AllStamp extends LitePalSupport implements Serializable {
    List<PerStamp> perStamps;

    public List<PerStamp> getPerStamps() {
        return perStamps;
    }

    public void setPerStamps(List<PerStamp> perStamps) {
        this.perStamps = perStamps;
    }

}
