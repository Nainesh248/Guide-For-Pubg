package com.adsum.guideforbattlegroundspubg.model;

public class DmrResponse {
    private String weponname,  damage;
    private int dmrimage;
    public DmrResponse() {
    }

    public DmrResponse(String weponname, String damage, int dmrimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.dmrimage = dmrimage;
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

    public int getDmrimage() {
        return dmrimage;
    }

    public void setDmrimage(int dmrimage) {
        this.dmrimage = dmrimage;
    }
}
