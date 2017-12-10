package com.duongton.camnangbabau.model;


import java.util.Date;

/**
 * Created by Theson on 11/21/2017.
 */

public class LapLich {
    private int id;
    private String date;
    private String time;
    private String note;
    private String type;
    private boolean isStatus;

    public LapLich() {
    }

    public LapLich(String date, String time, String note, String type, boolean isStatus) {
        this.date = date;
        this.time = time;
        this.note = note;
        this.type = type;
        this.isStatus = isStatus;
    }

    public LapLich(int id,String date, String time, String note) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }



    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }
}
