package com.forrest.nutriplanner.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meal_plan")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMealPlan;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "meal_plan_meal",
            joinColumns = @JoinColumn(name = "meal_plan_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Meal> meals = new ArrayList<>();

    private String description;
    private Integer energyTotal;
    private Integer proteinTotal;
    private Integer fatTotal;
    private Integer fiberTotal;
    private Integer carbohydratesTotal;
    private Integer sugarTotal;
    private Integer saltTotal;
    private Integer vitaminATotal;
    private Integer vitaminDTotal;
    private Integer vitaminETotal;
    private Integer vitaminKTotal;
    private Integer vitaminCTotal;
    private Integer vitaminB1Total;
    private Integer vitaminB2Total;
    private Integer vitaminPPTotal;
    private Integer vitaminB6Total;
    private Integer vitaminB9Total;
    private Integer vitaminB12Total;
}
