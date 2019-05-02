package com.thegoodthebadtheasian.myapplication.models.actionmodels;

import java.io.Serializable;

public class Notification implements Serializable {

    private String title;
    private String message;
    private int value;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
