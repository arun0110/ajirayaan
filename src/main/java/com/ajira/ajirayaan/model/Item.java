package com.ajira.ajirayaan.model;

public class Item {
    private InventoryItemType type;
    private int qty;

    public Item() {
    }

    public Item(InventoryItemType type, int qty) {
        this.type = type;
        this.qty = qty;
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
        return "Item{" +
                "type='" + type + '\'' +
                ", qty=" + qty +
                '}';
    }
}
