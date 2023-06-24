package com.forrest.nutriplanner.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue
    private Long idFood;
    private String productName;
    private String quantity;
    private String brand;
    private String ingredientText;
    private Integer energy100g;
    private Integer protein100g;
    private Integer fat100g;
    private Integer fiber100g;
    private Integer carbohydrates100g;
    private Integer sugar100g;
    private Integer salt100g;
    private Integer vitaminA;
    private Integer vitaminD;
    private Integer vitaminE;
    private Integer vitaminK;
    private Integer vitaminC;
    private Integer vitaminB;
    private Integer vitaminB2;
    private Integer vitaminPP;
    private Integer vitaminB6;
    private Integer vitaminB9;
    private Integer vitaminB12;


}
