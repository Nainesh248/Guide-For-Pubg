package com.adsum.guideforbattlegroundspubg.model;

public class ThrowabilesResponse {
    private String weponname,  damage;
    private int throwimage;
    public ThrowabilesResponse() {
    }

    public ThrowabilesResponse(String weponname, String damage, int throwimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.throwimage = throwimage;
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

    public int getThrowimage() {
        return throwimage;
    }

    public void setThrowimage(int throwimage) {
        this.throwimage = throwimage;
    }
}
