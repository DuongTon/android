package com.duongton.camnangbabau.model;

/**
 * Created by duong on 10/10/2017.
 */

public class DocTruyen {
    private int ma;
    private String ten;
    private String noiDung;

    public DocTruyen(int ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public DocTruyen(String noiDung) {
        this.noiDung = noiDung;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
