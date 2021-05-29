package com.adsum.guideforbattlegroundspubg.model;

public class HandgunsResponse {
    private String weponname,  damage;
    private int handgunimage;
    public HandgunsResponse() {
    }

    public HandgunsResponse(String weponname, String damage, int handgunimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.handgunimage = handgunimage;
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

    public int getHandgunimage() {
        return handgunimage;
    }

    public void setHandgunimage(int handgunimage) {
        this.handgunimage = handgunimage;
    }
}
