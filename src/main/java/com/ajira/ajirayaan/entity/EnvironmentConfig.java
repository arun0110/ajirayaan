package com.ajira.ajirayaan.entity;

import com.ajira.ajirayaan.model.TerrainType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EnvironmentConfig {
    @Id
    @GeneratedValue
    private Long id;
    private int temperature;
    private int humidity;
    private boolean solarFlare;
    private boolean storm;
    private String areaMap;

    public EnvironmentConfig() {
    }

    public EnvironmentConfig(Long id, int temperature, int humidity, boolean solarFlare, boolean storm, String areaMap) {
        this.id = id;
        this.temperature = temperature;
        this.humidity = humidity;
        this.solarFlare = solarFlare;
        this.storm = storm;
        this.areaMap = areaMap;
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

    public String getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(String areaMap) {
        this.areaMap = areaMap;
    }

    @Override
    public String toString() {
        return "EnvironmentConfig{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", solarFlare=" + solarFlare +
                ", storm=" + storm +
                ", areaMap='" + areaMap + '\'' +
                '}';
    }
}
