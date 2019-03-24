package com.example.dress.util.Letter;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AllLetter extends LitePalSupport implements Serializable {
    private Map<Integer,Integer> position_to_id;
    private Map<Integer,String> id_to_name=null;
    private Map<Integer,Integer> id_to_position=null;
    private List<PerLetter> allletter=null;
    private List<Letter> allFirstLetter=null;

    public List<PerLetter> getAllletter() {
        return allletter;
    }

    public void setAllletter(List<PerLetter> allletter) {
        this.allletter = allletter;
    }

    public List<Letter> getAllFirstLetter(){
        if(allFirstLetter==null){
            allFirstLetter = new ArrayList<Letter>();
            for(PerLetter letter:allletter){
                allFirstLetter.add(letter.getLastLetter());
            }
        }
        return allFirstLetter;
    }

    public void updateAllFirstLetter(){
        allFirstLetter = new ArrayList<Letter>();
        for(PerLetter letter:allletter){
            allFirstLetter.add(letter.getLastLetter());
        }
    }

    public Map<Integer, Integer> getPosition_to_id() {
        return position_to_id;
    }

    public void setPosition_to_id(Map<Integer, Integer> position_to_id) {
        this.position_to_id = position_to_id;
    }

    public Map<Integer, String> getId_to_name() {
        return id_to_name;
    }

    public void setId_to_name(Map<Integer, String> id_to_name) {
        this.id_to_name = id_to_name;
    }

    public Map<Integer, Integer> getId_to_position() {
        return id_to_position;
    }

    public void setId_to_position(Map<Integer, Integer> id_to_position) {
        this.id_to_position = id_to_position;
    }

    public void setAllFirstLetter(List<Letter> allFirstLetter) {
        this.allFirstLetter = allFirstLetter;
    }
}
