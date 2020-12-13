package com.ajira.ajirayaan.entity;

import com.ajira.ajirayaan.model.Action;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class StateAndAction {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Action action;

    public StateAndAction() {
    }

    public StateAndAction(Long id, String name, Action action) {
        this.id = id;
        this.name = name;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "StateAndAction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", action=" + action +
                '}';
    }
}
