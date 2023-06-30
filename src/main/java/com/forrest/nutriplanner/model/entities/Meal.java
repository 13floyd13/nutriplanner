package com.forrest.nutriplanner.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeal;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private Set<MealFood> mealFoods;

    @ManyToMany(mappedBy = "meals")
    private List<MealPlan> mealPlans = new ArrayList<>();

    private String description;
    private Integer mealEnergy;
    private Integer mealProtein;
    private Integer mealFat;
    private Integer mealFiber;
    private Integer mealCarbohydrates;
    private Integer mealSugar;
    private Integer mealSalt;
    private Integer mealVitaminA;
    private Integer mealVitaminD;
    private Integer mealVitaminE;
    private Integer mealVitaminK;
    private Integer mealVitaminC;
    private Integer mealVitaminB1;
    private Integer mealVitaminB2;
    private Integer mealVitaminPP;
    private Integer mealVitaminB6;
    private Integer mealVitaminB9;
    private Integer mealVitaminB12;
}
