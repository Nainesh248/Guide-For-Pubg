package com.adsum.guideforbattlegroundspubg.model;

public class MeleeResponse {
    private String weponname,  damage;
    private int meleeimage;
    public MeleeResponse() {
    }

    public MeleeResponse(String weponname, String damage, int meleeimage) {
        this.weponname = weponname;
        this.damage = damage;
        this.meleeimage = meleeimage;
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

    public int getMeleeimage() {
        return meleeimage;
    }

    public void setMeleeimage(int meleeimage) {
        this.meleeimage = meleeimage;
    }
}
