package com.ajira.ajirayaan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Direction {
    @JsonProperty("up")
    UP,
    @JsonProperty("down")
    DOWN,
    @JsonProperty("left")
    LEFT,
    @JsonProperty("right")
    RIGHT
}
