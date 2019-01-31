package com.example.dress.util;

import org.litepal.LitePal;

import java.util.List;

public class cache {
    private static Token token;
    private static User user;
    public static Token getCacheToken(){
        List<Token> tokens = LitePal.findAll(com.example.dress.util.Token.class);
        if(tokens.isEmpty()){
            return null;
        }
        token = tokens.get(0);
        return token;
    }
    public static User getUser(){
        if(user == null){
            user = new User();
        }
        return user;
    }
}
