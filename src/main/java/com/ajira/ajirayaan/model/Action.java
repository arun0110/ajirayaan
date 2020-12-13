package com.ajira.ajirayaan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Action {
    @JsonProperty("move")
    MOVE,
    @JsonProperty("collect-sample")
    COLLECT_SAMPLE
}
