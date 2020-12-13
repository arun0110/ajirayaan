package com.ajira.ajirayaan.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SampleType {
    @JsonProperty("water-sample")
    WATER_SAMPLE,
    @JsonProperty("rock-sample")
    ROCK_SAMPLE
}
