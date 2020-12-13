package com.ajira.ajirayaan.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
public class RoverStatus {
    @Id
    private Long id;
    private int row;
    private int col;
    private int battery;

    public RoverStatus() {
    }

    public RoverStatus(Long id, int row, int col, int battery) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.battery = battery;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    @Override
    public String toString() {
        return "RoverStatus{" +
                "id=" + id +
                ", row=" + row +
                ", col=" + col +
                ", battery=" + battery +
                '}';
    }
}
