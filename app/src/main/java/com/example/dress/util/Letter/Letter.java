package com.example.dress.util.Letter;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Letter extends LitePalSupport implements Serializable {
    private int stampviewid;
    private int receiverid;
    private int senderid;
    private String text;
    private String sender;
    private String receiver;
    private String timestamp;
    private int type;    // 0回复  1陌生人  2写个自己的信

    public int getStampviewid() {
        return stampviewid;
    }

    public void setStampviewid(int stampviewid) {
        this.stampviewid = stampviewid;
    }

    public int getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(int receiverid) {
        this.receiverid = receiverid;
    }

    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
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

    @Override
    public String toString() {
        return "Letter{" +
                "stampviewid=" + stampviewid +
                ", receiverid=" + receiverid +
                ", senderid=" + senderid +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", type=" + type +
                '}';
    }
}
