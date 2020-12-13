package com.ajira.ajirayaan.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Operator {
    @JsonProperty("eq")
    EQ,
    @JsonProperty("ne")
    NE,
    @JsonProperty("lte")
    LTE,
    @JsonProperty("gte")
    GTE,
    @JsonProperty("lt")
    LT,
    @JsonProperty("gt")
    GT

}
