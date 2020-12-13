package com.ajira.ajirayaan.model;

import com.ajira.ajirayaan.model.request.Operator;

public class ScenarioCondition {
    private String type;
    private String property;
    private Operator operator;
    private String value;

    public ScenarioCondition() {
    }

    public ScenarioCondition(String type, String property, Operator operator, String value) {
        this.type = type;
        this.property = property;
        this.operator = operator;
        this.value = value;
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
        return "ScenarioCondition{" +
                "type='" + type + '\'' +
                ", property='" + property + '\'' +
                ", operator=" + operator +
                ", value='" + value + '\'' +
                '}';
    }
}
