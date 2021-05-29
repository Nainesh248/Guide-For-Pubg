package com.adsum.guideforbattlegroundspubg.model;

public class LmgResponse {
    private String weponname,  damage;
    private int lmgimage;
    public LmgResponse() {
    }

    public LmgResponse(String weponname, String damage, int lmgimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.lmgimage = lmgimage;
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

    public int getLmgimage() {
        return lmgimage;
    }

    public void setLmgimage(int lmgimage) {
        this.lmgimage = lmgimage;
    }
}
