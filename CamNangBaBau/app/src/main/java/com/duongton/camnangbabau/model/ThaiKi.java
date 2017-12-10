package com.duongton.camnangbabau.model;

/**
 * Created by duong on 10/5/2017.
 */

public class ThaiKi {
    private String image;
    private String name;

    public ThaiKi(String name) {
        this.name = name;
    }

    public ThaiKi(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
