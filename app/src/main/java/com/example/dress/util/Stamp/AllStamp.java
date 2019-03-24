package com.example.dress.util.Stamp;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class AllStamp extends LitePalSupport implements Serializable {
    private int size;
    private int[] count;
    List<PerStamp> perStamps;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int[] count) {
        this.count = count;
    }

    public List<PerStamp> getPerStamps() {
        return perStamps;
    }

    public void setPerStamps(List<PerStamp> perStamps) {
        this.perStamps = perStamps;
    }

}
