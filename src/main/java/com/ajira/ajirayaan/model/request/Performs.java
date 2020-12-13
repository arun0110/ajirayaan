package com.ajira.ajirayaan.model.request;

import com.ajira.ajirayaan.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Performs {
    @JsonProperty("collect-sample")
    private Item collectSample;
    @JsonProperty("item-usage")
    private Item itemUsage;

    public Performs() {
    }

    public Performs(Item collectSample, Item itemUsage) {
        this.collectSample = collectSample;
        this.itemUsage = itemUsage;
    }

    public Item getCollectSample() {
        return collectSample;
    }

    public void setCollectSample(Item collectSample) {
        this.collectSample = collectSample;
    }

    public Item getItemUsage() {
        return itemUsage;
    }

    public void setItemUsage(Item itemUsage) {
        this.itemUsage = itemUsage;
    }

    @Override
    public String toString() {
        return "Performs{" +
                "collectSample=" + collectSample +
                ", itemUsage=" + itemUsage +
                '}';
    }
}
