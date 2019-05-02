package com.thegoodthebadtheasian.myapplication.models.actionmodels;

import java.io.Serializable;

public class GoogleAnalytics implements Serializable {

    private String eventCategory;
    private String eventAction;
    private String eventLabel;
    private int eventValue;

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public String getEventAction() {
        return eventAction;
    }

    public void setEventAction(String eventAction) {
        this.eventAction = eventAction;
    }

    public String getEventLabel() {
        return eventLabel;
    }

    public void setEventLabel(String eventLabel) {
        this.eventLabel = eventLabel;
    }

    public int getEventValue() {
        return eventValue;
    }

    public void setEventValue(int eventValue) {
        this.eventValue = eventValue;
    }
}
