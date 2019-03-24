package com.example.dress.util;

public class Stamp {
    private int imageSourse;            //图片的id
    private int groupIndex;             //第几组的下标
    private int index;                  //第几组的第几个的下标
    private int isGet=0;                  //是否获得邮票标记（1：获得/0：没有）//改为邮票数量
    private String text="";                //用来显示图片下面的话

    public Stamp(int imageSourse, int groupIndex, int index, int isGet) {
        this.imageSourse = imageSourse;
        this.groupIndex = groupIndex;
        this.index = index;
        this.isGet = isGet;
    }



    public int getImageSourse() {
        return imageSourse;
    }

    public void setImageSourse(int imageSourse) {
        this.imageSourse = imageSourse;
    }

    public int getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(int groupIndex) {
        this.groupIndex = groupIndex;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIsGet() {
        return isGet;
    }

    public void setIsGet(int isGet) {
        this.isGet = isGet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
