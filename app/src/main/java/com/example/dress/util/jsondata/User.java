package com.example.dress.util.jsondata;

import java.io.Serializable;

public class User implements Serializable {
    private String phone;
    private String username; //用户名
    private String id;

    public User(){
        super();
    }
    public User(String phone,String username){
        super();
        this.phone = phone;
        this.username = username;
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
}
