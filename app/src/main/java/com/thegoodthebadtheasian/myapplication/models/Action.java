package com.thegoodthebadtheasian.myapplication.models;

import java.io.Serializable;

public class Action implements Serializable {

    private String id;
    private String name;

    public Action(String id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
