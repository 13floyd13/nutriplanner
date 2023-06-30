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

    private String idOpenFoodFact;
    private String code;
    private String name;
    private String productName;
    private String quantity;
    private String brand;
    @Column(length = 1000)
    private String ingredients;
    private Integer energy100G;
    private Float protein100G;
    private Float fat100G;
    private Float saturatedFat100G;
    private Float transFat100G;
    private Float fiber100G;
    private Float carbohydrates100G;
    private Float sugar100G;
    private Float salt100G;
    private Float sodium100G;
    private Float calcium100G;
    private Float cholesterol100G;
    private Float iron100g;
    private Float vitaminA;
    private Float vitaminD;
    private Float vitaminE;
    private Float vitaminK;
    private Float vitaminC;
    private Float vitaminB;
    private Float vitaminB2;
    private Float vitaminPP;
    private Float vitaminB6;
    private Float vitaminB9;
    private Float vitaminB12;

}
