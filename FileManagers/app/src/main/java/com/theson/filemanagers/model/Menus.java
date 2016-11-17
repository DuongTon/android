package com.theson.filemanagers.model;


public class Menus {
    private int resId;
    private String name;

    public Menus(int resId, String name) {
        this.resId = resId;
        this.name = name;
    }

    public int getResId() {
        return resId;
    }

    public String getName() {
        return name;
    }
}
