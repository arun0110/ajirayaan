package com.ajira.ajirayaan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EnvironmentProperty {
    @JsonProperty("terrain")
    TERRAIN,
    @JsonProperty("temperature")
    TEMPERATURE,
    @JsonProperty("humidity")
    HUMIDITY,
    @JsonProperty("solar-flare")
    SOLAR_FLARE,
    @JsonProperty("storm")
    STORM
}
