package com.adsum.guideforbattlegroundspubg.model;

public class EquipmentResponse {
    private String weponname, capacity,damage,durability;
    private int equipmentimage;
    public EquipmentResponse() {
    }

    public EquipmentResponse(String weponname, String capacity, String damage, String durability, int equipmentimage) {
        this.weponname = weponname;
        this.capacity = capacity;
        this.damage = damage;
        this.durability = durability;
        this.equipmentimage = equipmentimage;
    }

    public String getWeponname() {
        return weponname;
    }

    public void setWeponname(String weponname) {
        this.weponname = weponname;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public String getDurability() {
        return durability;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public int getEquipmentimage() {
        return equipmentimage;
    }

    public void setEquipmentimage(int equipmentimage) {
        this.equipmentimage = equipmentimage;
    }
}
