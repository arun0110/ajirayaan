package com.ajira.ajirayaan.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvironmentUpdate{
    private Integer temperature;
    private Integer humidity;
    @JsonProperty("solar-flare")
    private Boolean solarFlare;
    private Boolean storm;
    private String  terrain;

    public EnvironmentUpdate() {
    }

    public EnvironmentUpdate(Integer temperature, Integer humidity, Boolean solarFlare, Boolean storm, String terrain) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.solarFlare = solarFlare;
        this.storm = storm;
        this.terrain = terrain;
    }


    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Boolean getSolarFlare() {
        return solarFlare;
    }

    public void setSolarFlare(Boolean solarFlare) {
        this.solarFlare = solarFlare;
    }

    public Boolean getStorm() {
        return storm;
    }

    public void setStorm(Boolean storm) {
        this.storm = storm;
    }


    public String getTerrain() {
        return terrain;
    }


    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "EnvironmentUpdate{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", solarFlare=" + solarFlare +
                ", storm=" + storm +
                ", terrain='" + terrain + '\'' +
                '}';
    }
}
