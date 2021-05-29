package com.adsum.guideforbattlegroundspubg.model;

public class SmgResponse {
    private String weponname,  damage;
    private int smgimage;
    public SmgResponse() {
    }

    public SmgResponse(String weponname, String damage, int smgimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.smgimage = smgimage;
    }

    public String getWeponname() {
        return weponname;
    }

    public void setWeponname(String weponname) {
        this.weponname = weponname;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public int getSmgimage() {
        return smgimage;
    }

    public void setSmgimage(int smgimage) {
        this.smgimage = smgimage;
    }
}
