package com.adsum.guideforbattlegroundspubg.model;

public class ConsumablesResponse {
    private String weponname, increase,timetaken,capacity;
    private int consumablesimage;
    public ConsumablesResponse() {
    }

    public ConsumablesResponse(String weponname, String increase, String timetaken, String capacity, int consumablesimage) {
        this.weponname = weponname;
        this.increase = increase;
        this.timetaken = timetaken;
        this.capacity = capacity;
        this.consumablesimage = consumablesimage;
    }

    public String getWeponname() {
        return weponname;
    }

    public void setWeponname(String weponname) {
        this.weponname = weponname;
    }

    public String getIncrease() {
        return increase;
    }

    public void setIncrease(String increase) {
        this.increase = increase;
    }

    public String getTimetaken() {
        return timetaken;
    }

    public void setTimetaken(String timetaken) {
        this.timetaken = timetaken;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getConsumablesimage() {
        return consumablesimage;
    }

    public void setConsumablesimage(int consumablesimage) {
        this.consumablesimage = consumablesimage;
    }
}
