package com.adsum.guideforbattlegroundspubg.model;

public class VehiclesResponse {
    private String weponname, health,topspeed;
    private int vehiclesimage;
    public VehiclesResponse() {
    }

    public VehiclesResponse(String weponname, String health, String topspeed, int vehiclesimage) {
        this.weponname = weponname;
        this.health = health;
        this.topspeed = topspeed;
        this.vehiclesimage = vehiclesimage;
    }

    public String getWeponname() {
        return weponname;
    }

    public void setWeponname(String weponname) {
        this.weponname = weponname;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getTopspeed() {
        return topspeed;
    }

    public void setTopspeed(String topspeed) {
        this.topspeed = topspeed;
    }

    public int getVehiclesimage() {
        return vehiclesimage;
    }

    public void setVehiclesimage(int vehiclesimage) {
        this.vehiclesimage = vehiclesimage;
    }
}
