package com.ajira.ajirayaan.model.request;

public class RoverScenario {
    private String is;
    private Performs performs;

    public RoverScenario() {
    }

    public RoverScenario(String is, Performs performs) {
        this.is = is;
        this.performs = performs;
    }

    public String getIs() {
        return is;
    }

    public void setIs(String is) {
        this.is = is;
    }

    public Performs getPerforms() {
        return performs;
    }

    public void setPerforms(Performs performs) {
        this.performs = performs;
    }

    @Override
    public String toString() {
        return "RoverScenario{" +
                "is='" + is + '\'' +
                ", performs=" + performs +
                '}';
    }
}
