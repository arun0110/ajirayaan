package com.ajira.ajirayaan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SampleType {
    @JsonProperty("water-sample")
    WATER_SAMPLE,
    @JsonProperty("rock-sample")
    ROCK_SAMPLE
}
