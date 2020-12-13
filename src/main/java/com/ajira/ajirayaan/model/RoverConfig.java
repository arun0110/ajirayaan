package com.ajira.ajirayaan.model;

import com.ajira.ajirayaan.entity.InventoryItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class RoverConfig {
    private List<Scenario> scenarios;
    private List<State> states;
    @JsonProperty("deploy-point")
    private Location deployPoint;
    @JsonProperty("initial-battery")
    private int initialBattery;
    private List<InventoryItem> inventory;

    public RoverConfig() {
    }

    public RoverConfig(List<Scenario> scenarios, List<State> states, Location deployPoint, int initialBattery, List<InventoryItem> inventory) {
        this.scenarios = scenarios;
        this.states = states;
        this.deployPoint = deployPoint;
        this.initialBattery = initialBattery;
        this.inventory = inventory;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public Location getDeployPoint() {
        return deployPoint;
    }

    public void setDeployPoint(Location deployPoint) {
        this.deployPoint = deployPoint;
    }

    public int getInitialBattery() {
        return initialBattery;
    }

    public void setInitialBattery(int initialBattery) {
        this.initialBattery = initialBattery;
    }

    public List<InventoryItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<InventoryItem> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "RoverConfig{" +
                "scenarios=" + scenarios +
                ", states=" + states +
                ", deployPoint=" + deployPoint +
                ", initialBattery=" + initialBattery +
                ", inventory=" + inventory +
                '}';
    }
}
