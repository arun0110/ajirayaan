package com.ajira.ajirayaan.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Environment {

    @Id
    private Long id;
    private int temperature;
    private int humidity;
    @JsonProperty("solar-flare")
    private boolean solarFlare;
    private boolean storm;
    private String  terrain;

    public Environment() {
    }

    public Environment(int temperature, int humidity, boolean solarFlare, boolean storm, String terrain) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.solarFlare = solarFlare;
        this.storm = storm;
        this.terrain = terrain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", solarFlare=" + solarFlare +
                ", storm=" + storm +
                ", terrain='" + terrain + '\'' +
                '}';
    }
}
