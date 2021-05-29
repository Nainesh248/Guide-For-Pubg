package com.adsum.guideforbattlegroundspubg.model;

public class MagazinesDetailResponse {
    private String magazinesname,  detail;
    private int magazinesimage;
    public MagazinesDetailResponse() {
    }

    public MagazinesDetailResponse(String magazinesname, String detail, int magazinesimage) {
        this.magazinesname = magazinesname;
        this.detail = detail;
        this.magazinesimage = magazinesimage;
    }

    public String getMagazinesname() {
        return magazinesname;
    }

    public void setMagazinesname(String magazinesname) {
        this.magazinesname = magazinesname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getMagazinesimage() {
        return magazinesimage;
    }

    public void setMagazinesimage(int magazinesimage) {
        this.magazinesimage = magazinesimage;
    }
}
