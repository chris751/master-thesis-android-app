package com.thegoodthebadtheasian.myapplication.models;

import java.io.Serializable;
import java.util.List;

public class Trigger implements Serializable {
    private String _id;
    private List<Sensor> sensors;

    public String get_id() {
        return _id;
    }

    public List<Sensor> getSensors () {
        return sensors;
    }
}
