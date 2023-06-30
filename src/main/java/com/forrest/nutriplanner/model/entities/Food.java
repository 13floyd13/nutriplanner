package com.forrest.nutriplanner.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue
    private Long idFood;

    @OneToMany(mappedBy = "food")
    private Set<MealFood> mealFood;

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
