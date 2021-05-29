package com.adsum.guideforbattlegroundspubg.model;

public class LowerRailsDetailsResponse {
    private String lowerrname,  detail;
    private int lowerrimage;
    public LowerRailsDetailsResponse() {
    }

    public LowerRailsDetailsResponse(String lowerrname, String detail, int lowerrimage) {
        this.lowerrname = lowerrname;
        this.detail = detail;
        this.lowerrimage = lowerrimage;
    }

    public String getLowerrname() {
        return lowerrname;
    }

    public void setLowerrname(String lowerrname) {
        this.lowerrname = lowerrname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getLowerrimage() {
        return lowerrimage;
    }

    public void setLowerrimage(int lowerrimage) {
        this.lowerrimage = lowerrimage;
    }
}
