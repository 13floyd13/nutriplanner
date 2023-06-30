package com.forrest.nutriplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NutrimentDTO {

    @JsonProperty("calcium_100g")
    private Float calcium100G;

    @JsonProperty("carbohydrates_100g")
    private Float carbohydrates100G;

    @JsonProperty("cholesterol_100g")
    private Float cholesterol100G;

    @JsonProperty("energy-kcal_100g")
    private int energyKcal100G;

    @JsonProperty("fat_100g")
    private Float fat100G;

    @JsonProperty("fiber_100g")
    private Float fiber100G;

    @JsonProperty("iron_100g")
    private Float iron100G;

    @JsonProperty("proteins_100g")
    private Float proteins100G;

    @JsonProperty("salt_100g")
    private Float salt100G;

    @JsonProperty("saturated-fat_100g")
    private Float saturatedFat100G;

    @JsonProperty("sodium_100g")
    private Float sodium100G;

    @JsonProperty("sugars_100g")
    private Float sugars100G;

    @JsonProperty("trans-fat_100g")
    private Float transFat100G;

    @JsonProperty("vitamin-a_100g")
    private Float vitaminA100G;

    @JsonProperty("vitamin-c_100g")
    private Float vitaminC100G;

    @JsonProperty("vitamin-d_100g")
    private Float vitaminD100G;

}
