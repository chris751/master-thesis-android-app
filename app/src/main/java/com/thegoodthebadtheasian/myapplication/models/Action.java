package com.thegoodthebadtheasian.myapplication.models;

import com.thegoodthebadtheasian.myapplication.models.actionmodels.GoogleAnalytics;
import com.thegoodthebadtheasian.myapplication.models.actionmodels.Notification;
import com.thegoodthebadtheasian.myapplication.models.actionmodels.SMS;

import java.io.Serializable;

public class Action implements Serializable {

    private String _id;
    private GoogleAnalytics ga;
    private SMS sms;
    private Notification notication;


    public GoogleAnalytics getGa() {
        return ga;
    }

    public void setGa(GoogleAnalytics ga) {
        this.ga = ga;

    }
    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }

    public Notification getNotication() {
        return notication;
    }

    public void setNotication(Notification notication) {
        this.notication = notication;
    }

    public String get_id() {
        return _id;
    }
}
