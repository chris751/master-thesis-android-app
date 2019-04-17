package com.thegoodthebadtheasian.myapplication.models;

import java.io.Serializable;

public class PlaceholderDevice implements Serializable {

    private String _id;
    private int price = 001;
    private String name = "johnson";

    public String getId(){
        return _id;
    }

    public int getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }

}
