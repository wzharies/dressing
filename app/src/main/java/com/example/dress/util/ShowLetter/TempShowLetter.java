package com.example.dress.util.ShowLetter;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class TempShowLetter extends LitePalSupport implements Serializable {
    private String title;
    private String text;
    private int tag;
    private int id;

    public TempShowLetter(String title, String text, int tag) {
        this.title = title;
        this.text = text;
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getIamgeSourse() {
        return id;
    }

    public void setIamgeSourse(int iamgeSourse) {
        this.id = iamgeSourse;
    }


}
