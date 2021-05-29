package com.adsum.guideforbattlegroundspubg.model;

public class BowsResponse {
    private String weponname,  damage;
    private int bowsimage;
    public BowsResponse() {
    }

    public BowsResponse(String weponname, String damage, int bowsimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.bowsimage = bowsimage;
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

    public int getBowsimage() {
        return bowsimage;
    }

    public void setBowsimage(int bowsimage) {
        this.bowsimage = bowsimage;
    }
}
