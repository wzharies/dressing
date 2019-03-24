package com.example.dress.util;

import android.widget.ImageView;

import java.io.Serializable;

public class Envelope implements Serializable {
        private int stampviewid;
        private String text;
        private String sender;
        private String receiver;
        private String timestamp;
        private int type;    // 0回复  1陌生人  2写个自己的信

        public Envelope(){

        }

    public Envelope(String text,String sender,String receiver,int stampViewId){
        this.receiver=receiver;
        this.sender=sender;
        this.stampviewid=stampViewId;
        this.text=text;
    }
    //get参数
    public int getStampViewId() {
        return stampviewid;
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
        this.stampviewid = stampViewId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
