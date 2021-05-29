package com.adsum.guideforbattlegroundspubg.model;

public class MuzzlesResponse {
    private String muzzlename,  detail;
    private int muzzlesimage;
    public MuzzlesResponse() {
    }

    public MuzzlesResponse(String muzzlename, String detail, int muzzlesimage) {
        this.muzzlename = muzzlename;
        this.detail = detail;
        this.muzzlesimage = muzzlesimage;
    }

    public String getMuzzlename() {
        return muzzlename;
    }

    public void setMuzzlename(String muzzlename) {
        this.muzzlename = muzzlename;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getMuzzlesimage() {
        return muzzlesimage;
    }

    public void setMuzzlesimage(int muzzlesimage) {
        this.muzzlesimage = muzzlesimage;
    }
}
