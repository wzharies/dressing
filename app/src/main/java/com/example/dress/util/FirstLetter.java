package com.example.dress.util;

import java.util.ArrayList;
import java.util.Map;

public class FirstLetter {
    Map<String,Integer> person;
    ArrayList<Envelope> perletter;
    ArrayList<ArrayList<Envelope>> Allletter;

    public Map<String, Integer> getPerson() {
        return person;
    }

    public void setPerson(Map<String, Integer> person) {
        this.person = person;
    }

    public ArrayList<ArrayList<Envelope>> getAllletter() {
        return Allletter;
    }

    public void setALlletter(ArrayList<ArrayList<Envelope>> Allletter) {
        this.Allletter = Allletter;
    }

    public int getnumber(String receiver){
        return person.get(receiver);
    }

    public ArrayList<Envelope>  getperLetter(String recevier){
        return Allletter.get(getnumber(recevier));
    }

    public int getCount(String recevier){
        perletter = getperLetter(recevier);
        return perletter.size();
    }
}
