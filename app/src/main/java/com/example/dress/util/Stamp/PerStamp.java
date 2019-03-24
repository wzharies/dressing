package com.example.dress.util.Stamp;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class PerStamp  extends LitePalSupport implements Serializable {
    private String text;
    private int count;
    private int have;
    private int order;
    private List<tempStamp> stamps;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<tempStamp> getStamps() {
        return stamps;
    }

    public void setStamps(List<tempStamp> stamps) {
        this.stamps = stamps;
    }

    public int getHave() {
        return have;
    }

    public void setHave(int have) {
        this.have = have;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
