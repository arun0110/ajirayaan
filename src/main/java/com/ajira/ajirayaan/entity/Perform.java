package com.ajira.ajirayaan.entity;

import com.ajira.ajirayaan.model.InventoryItemType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Perform {
    @Id
    @GeneratedValue
    private Long id;
    private String scenario;
    private String roverIs;
    private String name;
    private InventoryItemType type;
    private int qty;

    public Perform() {
    }

    public Perform(Long id, String roverIs, String name, InventoryItemType type, int qty) {
        this.id = id;
        this.roverIs = roverIs;
        this.name = name;
        this.type = type;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getRoverIs() {
        return roverIs;
    }

    public void setRoverIs(String roverIs) {
        this.roverIs = roverIs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InventoryItemType getType() {
        return type;
    }

    public void setType(InventoryItemType type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Perform{" +
                "id=" + id +
                ", scenario='" + scenario + '\'' +
                ", roverIs='" + roverIs + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", qty=" + qty +
                '}';
    }
}
