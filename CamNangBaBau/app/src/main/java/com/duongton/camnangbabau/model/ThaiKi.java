package com.duongton.camnangbabau.model;

/**
 * Created by duong on 10/5/2017.
 */

public class ThaiKi {
    private int id;
    private String image;
    private String name;

    public ThaiKi(String name) {
        this.name = name;
    }

    public ThaiKi(int id,String image, String name) {
        this.id = id;
        this.image = image;
        this.name = name;
    }

    public ThaiKi(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
