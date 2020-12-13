package com.ajira.ajirayaan.model;

import com.ajira.ajirayaan.model.request.Action;

import java.util.List;

public class State {
    private String name;
    private List<Action> allowedActions;

    public State() {
    }

    public State(String name, List<Action> allowedActions) {
        this.name = name;
        this.allowedActions = allowedActions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Action> getAllowedActions() {
        return allowedActions;
    }

    public void setAllowedActions(List<Action> allowedActions) {
        this.allowedActions = allowedActions;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                ", allowedActions=" + allowedActions +
                '}';
    }
}
