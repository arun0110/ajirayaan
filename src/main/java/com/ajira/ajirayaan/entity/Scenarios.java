package com.ajira.ajirayaan.entity;

import com.ajira.ajirayaan.model.request.Operator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Scenarios {
    @Id
    @GeneratedValue
    private Long id;
    private String scenario;
    private String type;
    private String property;
    private Operator operator;
    private String value;


    public Scenarios() {
    }

    public Scenarios(Long id, String scenario, String type, String property, Operator operator, String value) {
        this.id = id;
        this.scenario = scenario;
        this.type = type;
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Scenarios{" +
                "id=" + id +
                ", scenario='" + scenario + '\'' +
                ", type='" + type + '\'' +
                ", property='" + property + '\'' +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
