package com.duongton.camnangbabau.model;

/**
 * Created by duong on 10/7/2017.
 */

public class AnUong {
    private int id;
    private String image;
    private String name;
    private String kiemTra;
    private String noiDung;

    public AnUong(String name, String noiDung) {
        this.name = name;
        this.noiDung = noiDung;
    }

    public AnUong(int id, String image, String name, String kiemTra) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.kiemTra = kiemTra;
    }

    public int getId() {
        return id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
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

    public String getKiemTra() {
        return kiemTra;
    }

    public void setKiemTra(String kiemTra) {
        this.kiemTra = kiemTra;
    }
}
