package com.ajira.ajirayaan.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


public class EnvironmentConfig {
    private int temperature;
    private int humidity;
    @JsonProperty("solar-flare")
    private boolean solarFlare;
    private boolean storm;
    @JsonProperty("area-map")
    private List<List<TerrainType>> areaMap = new ArrayList<>();

    public EnvironmentConfig() {
    }

    public EnvironmentConfig(int temperature, int humidity, boolean solarFlare, boolean storm) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.solarFlare = solarFlare;
        this.storm = storm;

    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public boolean isSolarFlare() {
        return solarFlare;
    }

    public void setSolarFlare(boolean solarFlare) {
        this.solarFlare = solarFlare;
    }

    public boolean isStorm() {
        return storm;
    }

    public void setStorm(boolean storm) {
        this.storm = storm;
    }

    public List<List<TerrainType>> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(List<List<TerrainType>> areaMap) {
        this.areaMap = areaMap;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", solarFlare=" + solarFlare +
                ", storm=" + storm +
                ", areaMap=" + areaMap +
                '}';
    }
}
