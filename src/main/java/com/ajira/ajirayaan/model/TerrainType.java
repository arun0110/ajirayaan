package com.ajira.ajirayaan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TerrainType {
    @JsonProperty("dirt")
    DIRT,
    @JsonProperty("water")
    WATER,
    @JsonProperty("rock")
    ROCK,
    @JsonProperty("sand")
    SAND;
}
