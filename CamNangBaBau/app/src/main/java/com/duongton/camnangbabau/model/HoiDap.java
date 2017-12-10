package com.duongton.camnangbabau.model;

/**
 * Created by Theson on 11/13/2017.
 */

public class HoiDap {
    private long id;
    private String cauHoi;
    private String noiDung;

    public HoiDap(long id, String cauHoi, String noiDung) {
        this.id = id;
        this.cauHoi = cauHoi;
        this.noiDung = noiDung;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }
}
