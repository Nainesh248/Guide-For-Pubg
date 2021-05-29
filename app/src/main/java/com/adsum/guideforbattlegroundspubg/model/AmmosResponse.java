package com.adsum.guideforbattlegroundspubg.model;

public class AmmosResponse {
    private String weponname,  detail;
    private int ammosimage;
    public AmmosResponse() {
    }

    public AmmosResponse(String weponname, String detail, int ammosimage) {
        this.weponname = weponname;
        this.detail = detail;
        this.ammosimage = ammosimage;
    }

    public String getWeponname() {
        return weponname;
    }

    public void setWeponname(String weponname) {
        this.weponname = weponname;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getAmmosimage() {
        return ammosimage;
    }

    public void setAmmosimage(int ammosimage) {
        this.ammosimage = ammosimage;
    }
}
