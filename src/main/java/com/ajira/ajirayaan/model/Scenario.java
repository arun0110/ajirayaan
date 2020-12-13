package com.ajira.ajirayaan.model;

import com.ajira.ajirayaan.model.request.RoverScenario;

import java.util.List;

public class Scenario {
    private String name;
    private List<ScenarioCondition> conditions;
    private List<RoverScenario> rover;

    public Scenario() {
    }

    public Scenario(String name, List<ScenarioCondition> conditions, List<RoverScenario> rover) {
        this.name = name;
        this.conditions = conditions;
        this.rover = rover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ScenarioCondition> getConditions() {
        return conditions;
    }

    public void setConditions(List<ScenarioCondition> conditions) {
        this.conditions = conditions;
    }

    public List<RoverScenario> getRover() {
        return rover;
    }

    public void setRover(List<RoverScenario> rover) {
        this.rover = rover;
    }

    @Override
    public String toString() {
        return "Scenario{" +
                "name='" + name + '\'' +
                ", conditions=" + conditions +
                ", rover=" + rover +
                '}';
    }
}
