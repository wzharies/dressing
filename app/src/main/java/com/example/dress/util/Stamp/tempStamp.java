package com.example.dress.util.Stamp;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class tempStamp  extends LitePalSupport implements Serializable {
    private int id;
    private String text;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "tempStamp{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
