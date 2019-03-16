package com.example.dress.util;



import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class User extends LitePalSupport implements Serializable {
    private int i_d;
    private String phone;
    private String username; //用户名
    private String signature;
    private int sex;
    private String token;

    public User(){
        super();
    }
    public User(String phone,String username,int id,String password){
        super();
        this.phone = phone;
        this.username = username;
        this.i_d=id;
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

    public int getId() {
        return i_d;
    }

    public User setId(int id) {
        this.i_d = id;
        return this;
    }


    public String getSignature() {
        return signature;
    }

    public User setSignature(String signature) {
        this.signature = signature;
        return this;
    }

    public int getSex() {
        return sex;
    }

    public User setSex(int sex) {
        this.sex = sex;
        return this;
    }

    public String getToken() {
        return token;
    }

    public User setToken(String token) {
        this.token = token;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + i_d + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", signature='" + signature + '\'' +
                ", sex=" + sex +
                ", token='" + token + '\'' +
                '}';
    }
}
