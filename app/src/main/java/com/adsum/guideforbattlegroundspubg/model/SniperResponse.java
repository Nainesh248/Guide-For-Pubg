package com.adsum.guideforbattlegroundspubg.model;

public class SniperResponse {
    private String weponname,  damage;
    private int sniperimage;
    public SniperResponse() {
    }

    public SniperResponse(String weponname, String damage, int sniperimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.sniperimage = sniperimage;
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

    public int getSniperimage() {
        return sniperimage;
    }

    public void setSniperimage(int sniperimage) {
        this.sniperimage = sniperimage;
    }
}
