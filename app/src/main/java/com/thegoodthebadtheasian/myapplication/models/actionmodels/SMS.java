package com.thegoodthebadtheasian.myapplication.models.actionmodels;

import java.io.Serializable;

public class SMS implements Serializable {

    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
