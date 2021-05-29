package com.adsum.guideforbattlegroundspubg.model;

public class ShotgunsResponse {
    private String weponname,  damage;
    private int shotgunimage;
    public ShotgunsResponse() {
    }

    public ShotgunsResponse(String weponname, String damage, int shotgunimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.shotgunimage = shotgunimage;
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

    public int getShotgunimage() {
        return shotgunimage;
    }

    public void setShotgunimage(int shotgunimage) {
        this.shotgunimage = shotgunimage;
    }
}
