package com.example.dress.util.Letter;

import com.example.dress.util.Letter.Letter;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.List;

public class PerLetter extends LitePalSupport implements Serializable {
    int receiveid;
    List<Letter> perletter;

    public PerLetter(int receiveid, List<Letter> perletter) {
        this.receiveid = receiveid;
        this.perletter = perletter;
    }

    public int getReceiveid() {
        return receiveid;
    }

    public void setReceiveid(int receiveid) {
        this.receiveid = receiveid;
    }

    public List<Letter> getPerletter() {
        return perletter;
    }

    public void setPerletter(List<Letter> perletter) {
        this.perletter = perletter;
    }

    public Letter getFirstLetter(){
        return perletter.get(0);
    }

    public Letter getLastNotMeLetter(){
        for(int i = perletter.size()-1;i>=0;i--){
            if(perletter.get(i).getReceiverid()!=receiveid){
                return perletter.get(i);
            }
        }
        return null;
    }
}
