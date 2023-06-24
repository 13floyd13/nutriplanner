package com.forrest.nutriplanner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MealFood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMealFood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_food")
    private Food food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    private Integer quantity;
}
