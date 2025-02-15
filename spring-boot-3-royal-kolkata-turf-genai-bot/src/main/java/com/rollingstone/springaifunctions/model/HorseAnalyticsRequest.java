package com.rollingstone.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Created by Binit Datta.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Horse Analytics API request")
public record HorseAnalyticsRequest(@JsonProperty(required = true,
        value = "question") @JsonPropertyDescription("The original text question") String question){
}
