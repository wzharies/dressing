package com.example.dress.util;

import android.widget.ImageView;

import java.io.Serializable;

public class Envelope implements Serializable {
        private String text;
        private String sender;
        private String receiver;
        private int stampViewId;

    public Envelope(String text,String sender,String receiver,int stampViewId){
        this.receiver=receiver;
        this.sender=sender;
        this.stampViewId=stampViewId;
        this.text=text;
    }
    //get参数
    public int getStampViewId() {
        return stampViewId;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }

        //set参数
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setStampViewId(int stampViewId) {
        this.stampViewId = stampViewId;
    }

    public void setText(String text) {
        this.text = text;
    }
}
