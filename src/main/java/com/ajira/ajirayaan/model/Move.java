package com.ajira.ajirayaan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Move {
    @JsonProperty("direction")
    private Direction direction;

    public Move() {
    }

    public Move(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Move{" +
                "direction=" + direction +
                '}';
    }
}
