package com.example.dress.util.jsondata;

import java.io.Serializable;

public class User implements Serializable {
    private String phone;
    private String username; //用户名
    private String id;
    private String signature;


    public User(){
        super();
    }
    public User(String phone,String username,String id,String signature){
        super();
        this.phone = phone;
        this.username = username;
        this.id=id;
        this.signature=signature;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }
    public void setSignature(String signature){
        this.signature=signature;
    }
    public String getSignature(){
        return signature;
    }

}
