package com.thegoodthebadtheasian.myapplication.models.actionmodels;

import java.io.Serializable;

public class SMS implements Serializable {

    private String phoneNumber;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
