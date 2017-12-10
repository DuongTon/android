package com.duongton.camnangbabau.model;

/**
 * Created by duong on 10/10/2017.
 */

public class DatTen {
    private int id;
    private String ten;
    private String yNghia;
    private String tenKhac;

    public DatTen(int id, String ten, String yNghia, String tenKhac) {
        this.id = id;
        this.ten = ten;
        this.yNghia = yNghia;
        this.tenKhac = tenKhac;
    }

    public DatTen(String ten) {
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getyNghia() {
        return yNghia;
    }

    public void setyNghia(String yNghia) {
        this.yNghia = yNghia;
    }

    public String getTenKhac() {
        return tenKhac;
    }

    public void setTenKhac(String tenKhac) {
        this.tenKhac = tenKhac;
    }
}
