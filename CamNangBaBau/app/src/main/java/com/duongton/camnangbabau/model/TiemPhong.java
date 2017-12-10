package com.duongton.camnangbabau.model;

/**
 * Created by duong on 10/7/2017.
 */

public class TiemPhong {
    private String name;
    private String content;

    public TiemPhong(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
