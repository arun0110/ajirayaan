package com.ajira.ajirayaan.model;

import com.ajira.ajirayaan.entity.Environment;

public class Status {
    private Rover rover;
    private Environment environment;

    public Status() {
    }

    public Status(Rover rover, Environment environment) {
        this.rover = rover;
        this.environment = environment;
    }

    public Rover getRover() {
        return rover;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return "Status{" +
                "rover=" + rover +
                ", environment=" + environment +
                '}';
    }
}
