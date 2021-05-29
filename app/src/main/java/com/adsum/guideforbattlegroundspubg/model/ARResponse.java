package com.adsum.guideforbattlegroundspubg.model;

public class ARResponse {
    private String weponname,  damage;
    private int ARimage;
    public ARResponse() {
    }

    public ARResponse(String weponname, String damage, int ARimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.ARimage = ARimage;
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

    public int getARimage() {
        return ARimage;
    }

    public void setARimage(int ARimage) {
        this.ARimage = ARimage;
    }
}
