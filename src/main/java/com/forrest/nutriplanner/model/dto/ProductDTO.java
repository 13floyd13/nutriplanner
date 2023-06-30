package com.forrest.nutriplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDTO {

    @JsonProperty("_id")
    private String id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("brands")
    private String brands;

    @JsonProperty("name_fr")
    private String name;

    @JsonProperty("ingredients_text")
    private String ingredients;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("quantity")
    private String quantity;

    private NutrimentDTO nutriments;
}
