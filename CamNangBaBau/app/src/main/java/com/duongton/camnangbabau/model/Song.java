package com.duongton.camnangbabau.model;

/**
 * Created by duong on 10/23/2017.
 */

public class Song {
    private String name;
    private int file;

    public Song(String name, int file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFile() {
        return file;
    }

    public void setFile(int file) {
        this.file = file;
    }
}
