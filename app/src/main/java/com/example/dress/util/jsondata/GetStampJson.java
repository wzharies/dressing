package com.example.dress.util.jsondata;

public class GetStampJson implements Comparable<GetStampJson>{
    int id_1;
    int id_2;
    String name;
    String text;

    public int getId_1() {
        return id_1;
    }

    public void setId_1(int id_1) {
        this.id_1 = id_1;
    }

    public int getId_2() {
        return id_2;
    }

    public void setId_2(int id_2) {
        this.id_2 = id_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public int compareTo(GetStampJson o) {
        if(this.id_1>o.getId_1()){
            return 1;
        }else if(this.id_1<o.getId_1()){
            return -1;
        }
        if(this.id_2>o.getId_2()){
            return 1;
        }else if(this.id_2<o.getId_2()){
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "GetStampJson{" +
                "id_1=" + id_1 +
                ", id_2=" + id_2 +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
