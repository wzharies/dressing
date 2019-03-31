package com.example.dress.util;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dress.util.Letter.AllLetter;
import com.example.dress.util.ShowLetter.AllShowLetter;
import com.example.dress.util.ShowLetter.ShowLetterGroup;
import com.example.dress.util.ShowLetter.TempShowLetter;
import com.example.dress.util.Stamp.AllStamp;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class cache {
    private static AllLetter letters=null;
    private static User user=null;
    private static AllStamp allStamp=null;
    private static int[][] stampcount=null;
    private static ArrayList<Integer> whichhava;
    private static int money=-1;

    private static List<TempShowLetter> showLetterList = new ArrayList<>();
    private static AllShowLetter allShowLetter=null; //用来存放所有展示的信件

    public static void getCache(){
        SQLiteDatabase db = LitePal.getDatabase();
        letters = LitePal.findFirst(AllLetter.class);
        user = LitePal.findFirst(User.class);
    }

    public static User getUser(){
        //绕过登陆界面
       user = new User("12321","shit",2,"123");
        return user;
    }
    public static void setUser(User ur){
        user = ur;
    }

    public static AllLetter getLetters() {
        return letters;
    }

    public static void setLetters(AllLetter letters) {
        cache.letters = letters;
    }

    public static AllStamp getAllStamp() {
        if(allStamp==null){
            DataInitial.initStamp();
        }
        return allStamp;
    }

    public static void setAllStamp(AllStamp allStamp) {
        cache.allStamp = allStamp;
    }

    public static int[][] getStampcount() {
        if(stampcount==null){
            DataInitial.initDate();
        }
        return stampcount;
    }

    public static void setStampcount(int[][] stampcount) {
        cache.stampcount = stampcount;
        initwhichhave();
    }

    public static int getMoney() {
        if(money==-1){
            DataInitial.initDate();
        }
        return money;
    }

    public static void setMoney(int money) {
        cache.money = money;
    }

    public static ArrayList<Integer> getWhichhava() {
        if(whichhava==null){
            initwhichhave();
        }
        return whichhava;
    }

    public static void setWhichhava(ArrayList<Integer> whichhava) {
        cache.whichhava = whichhava;
    }

    public static void initwhichhave(){
        whichhava = new ArrayList<>();
        for(int i =0;i<stampcount.length;i++){
            for(int j=0;j<stampcount[i].length;j++){
                //System.out.println(stampcount[i][j]);
                if(stampcount[i][j]!=0){
                    whichhava.add(i);
                    Log.i("whichhava",i+"-");
                    break;
                }
            }
        }

    }

    //下面是关于展示信件的函数
    public static AllShowLetter getAllShowLetter() {
        TempShowLetter a1 = new TempShowLetter("标题1","i m tag 1shafioajewfljlas",1);
        TempShowLetter a2 = new TempShowLetter("标yy1","i m tag 1sfdasfhafioajewfljlas",1);
        TempShowLetter b1 = new TempShowLetter("标题2","i m tag 2shafioajewfljlas",2);
        TempShowLetter b2 = new TempShowLetter("标yy2","i m tag 2sfdasfhafioajewfljlas",2);
        TempShowLetter c1 = new TempShowLetter("标题3","i m tag 3shafioajewfljlas",3);
        TempShowLetter c2 = new TempShowLetter("标yy3","i m tag 3sfdasfhafioajewfljlas",3);
        showLetterList.add(a1);
        showLetterList.add(a2);
        showLetterList.add(b1);
        showLetterList.add(b2);
        showLetterList.add(c1);
        showLetterList.add(c2);
        if(allShowLetter==null){
            //DataInitial.initStamp();
            //娶到的数据放到showLetterList里面
            //下面是进行分类的函数
            List<TempShowLetter> letters_1 = new ArrayList<>();
            List<TempShowLetter> letters_2 = new ArrayList<>();
            List<TempShowLetter> letters_3 = new ArrayList<>();
            for(int i=0;i<showLetterList.size();i++){
                TempShowLetter tempShowLetter = showLetterList.get(i);
                int tag = tempShowLetter.getTag();
                if(tag==1){
                    letters_1.add(tempShowLetter);
                }else if(tag==2){
                    letters_2.add(tempShowLetter);
                }else if(tag==3){
                    letters_3.add(tempShowLetter);
                }
                ShowLetterGroup group1 = new ShowLetterGroup(1,letters_1);
                ShowLetterGroup group2 = new ShowLetterGroup(2,letters_2);
                ShowLetterGroup group3 = new ShowLetterGroup(3,letters_3);
                List<ShowLetterGroup> tempLetterList = new ArrayList<>();
                tempLetterList.add(group1);
                tempLetterList.add(group2);
                tempLetterList.add(group3);
                allShowLetter = new AllShowLetter(tempLetterList);

            }
        }
        return allShowLetter;
    }


}
