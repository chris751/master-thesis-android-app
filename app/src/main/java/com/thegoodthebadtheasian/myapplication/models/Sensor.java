package com.thegoodthebadtheasian.myapplication.models;

import java.io.Serializable;

public class Sensor implements Serializable {
    private String _id;
    private String type;
    private Condition condition;

    public String get_id(){
        return _id;
    }

    public String getType() {
        return type;
    }

    public Condition getCondition(){
        return condition;
    }
}
