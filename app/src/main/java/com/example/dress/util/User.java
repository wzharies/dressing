package com.example.dress.util;

import java.io.Serializable;

public class User implements Serializable {
    private String phone;
    private String username; //用用户名
    private String password; //密码
    User(){
        super();
    }
    User(String phone,String username, String password){
        super();
        this.phone = phone;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
