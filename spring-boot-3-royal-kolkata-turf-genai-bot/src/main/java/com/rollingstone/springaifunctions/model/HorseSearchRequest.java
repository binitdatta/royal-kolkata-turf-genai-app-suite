package com.rollingstone.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

/**
 * Created by Binit Datta.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Horse Search API request")
public record HorseSearchRequest(@JsonProperty(required = true,
        value = "question") @JsonPropertyDescription("The original text question") String question,
        @JsonProperty(required = false,value = "birthDate") @JsonPropertyDescription("The birthDate") String birthDate,
        @JsonProperty(required = false, value = "color") @JsonPropertyDescription("The color") String color,
        @JsonProperty(required = false, value = "gender") @JsonPropertyDescription("The gender") String gender,
        @JsonProperty(required = false, value = "category") @JsonPropertyDescription("The gender") String category,
        @JsonProperty(required = false, value = "handicapRating") @JsonPropertyDescription("The handicapRating") String handicapRating){ //
}
