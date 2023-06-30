package com.forrest.nutriplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NutrimentDTO {

    @JsonProperty("calcium_100g")
    private float calcium100G;

    @JsonProperty("carbohydrates_100g")
    private float carbohydrates100G;

    @JsonProperty("cholesterol_100g")
    private float cholesterol100G;

    @JsonProperty("energy-kcal_100g")
    private int energyKcal100G;

    @JsonProperty("fat_100g")
    private float fat100G;

    @JsonProperty("fiber_100g")
    private float fiber100G;

    @JsonProperty("iron_100g")
    private float iron100G;

    @JsonProperty("nova-group_100g")
    private float novaGroup100G;

    @JsonProperty("proteins_100g")
    private float proteins100G;

    @JsonProperty("salt_100g")
    private float salt100G;

    @JsonProperty("saturated-fat_100g")
    private float saturatedFat100G;

    @JsonProperty("sodium_100g")
    private float sodium100G;

    @JsonProperty("sugars_100g")
    private float sugars100G;

    @JsonProperty("trans-fat_100g")
    private float transFat100G;

    @JsonProperty("vitamin-a_100g")
    private float vitaminA100G;

    @JsonProperty("vitamin-c_100g")
    private float vitaminC100G;

    @JsonProperty("vitamin-d_100g")
    private float vitaminD100G;

}
