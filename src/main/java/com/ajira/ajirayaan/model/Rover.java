package com.ajira.ajirayaan.model;

import com.ajira.ajirayaan.entity.InventoryItem;

import java.util.List;

public class Rover {
    private Location location;
    private int battery;
    private List<InventoryItem> inventory;

    public Rover() {
    }

    public Rover(Location location, int battery, List<InventoryItem> inventory) {
        this.location = location;
        this.battery = battery;
        this.inventory = inventory;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Rover{" +
                "location=" + location +
                ", battery=" + battery +
                ", inventory=" + inventory +
                '}';
    }
}
