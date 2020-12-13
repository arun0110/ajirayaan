package com.ajira.ajirayaan.entity;

import com.ajira.ajirayaan.model.request.InventoryItemType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private InventoryItemType type;
    @JsonProperty("quantity")
    private int qty;
    private int priority;

    public InventoryItem() {
    }

    public InventoryItem(InventoryItemType type, int qty, int priority) {
        this.type = type;
        this.qty = qty;
        this.priority = priority;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "type=" + type +
                ", qty=" + qty +
                ", priority=" + priority +
                '}';
    }
}
