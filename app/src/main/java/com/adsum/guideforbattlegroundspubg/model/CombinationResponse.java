package com.adsum.guideforbattlegroundspubg.model;

public class CombinationResponse {
    private String combinationname,  detail;
    private int combinationimage;
    public CombinationResponse() {
    }

    public CombinationResponse(String combinationname, String detail, int combinationimage) {
        this.combinationname = combinationname;
        this.detail = detail;
        this.combinationimage = combinationimage;
    }

    public String getCombinationname() {
        return combinationname;
    }

    public void setCombinationname(String combinationname) {
        this.combinationname = combinationname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCombinationimage() {
        return combinationimage;
    }

    public void setCombinationimage(int combinationimage) {
        this.combinationimage = combinationimage;
    }
}
