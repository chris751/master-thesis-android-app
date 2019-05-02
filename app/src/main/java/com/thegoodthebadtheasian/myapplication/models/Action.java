package com.thegoodthebadtheasian.myapplication.models;

import com.thegoodthebadtheasian.myapplication.models.actionmodels.GoogleAnalytics;
import com.thegoodthebadtheasian.myapplication.models.actionmodels.Notification;
import com.thegoodthebadtheasian.myapplication.models.actionmodels.SMS;

import java.io.Serializable;

public class Action implements Serializable {

    private String _id;
    private GoogleAnalytics googleAnalytics;
    private SMS sms;
    private Notification notification;


    public GoogleAnalytics getGoogleAnalytics() {
        return googleAnalytics;
    }

    public void setGoogleAnalytics(GoogleAnalytics googleAnalytics) {
        this.googleAnalytics = googleAnalytics;

    }
    public SMS getSms() {
        return sms;
    }

    public void setSms(SMS sms) {
        this.sms = sms;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String get_id() {
        return _id;
    }
}
