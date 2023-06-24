package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.MealFood;

import java.util.List;

public interface MealFoodService {
    MealFood saveMealFood(MealFood mealFood);
    MealFood updateMealFood(MealFood mealFood);
    MealFood getMealFoodById(Long id);
    void deleteMealFoodById(Long id);
    List<MealFood> getAllMealFoods();
}
