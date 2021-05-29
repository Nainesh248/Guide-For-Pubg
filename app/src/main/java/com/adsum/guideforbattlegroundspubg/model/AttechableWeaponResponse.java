package com.adsum.guideforbattlegroundspubg.model;

public class AttechableWeaponResponse {
    private String attechweaponname,  detail;
    private int attechweaponimage;
    public AttechableWeaponResponse() {
    }

    public AttechableWeaponResponse(String attechweaponname, String detail, int attechweaponimage) {
        this.attechweaponname = attechweaponname;
        this.detail = detail;
        this.attechweaponimage = attechweaponimage;
    }

    public String getAttechweaponname() {
        return attechweaponname;
    }

    public void setAttechweaponname(String attechweaponname) {
        this.attechweaponname = attechweaponname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAttechweaponimage() {
        return attechweaponimage;
    }

    public void setAttechweaponimage(int attechweaponimage) {
        this.attechweaponimage = attechweaponimage;
    }
}
