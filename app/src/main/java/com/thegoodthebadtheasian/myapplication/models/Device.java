package com.thegoodthebadtheasian.myapplication.models;

import java.io.Serializable;
import java.util.List;

public class Device implements Serializable {
    private String _id;
    private Trigger trigger;
    private Action action;

    public String get_id() {
        return _id;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Action getAction(){
        return action;
    }
}
