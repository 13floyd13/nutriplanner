package com.forrest.nutriplanner.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResponseDTO {
    @JsonProperty("code")
    private String code;

    @JsonProperty("product")
    private ProductDTO product;
}
