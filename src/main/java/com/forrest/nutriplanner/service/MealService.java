package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.entities.Meal;

import java.util.List;

public interface MealService {
    Meal saveMeal(Meal meal);
    Meal updateMeal(Meal meal);
    Meal getMealById(Long id);
    void deleteMealById(Long id);
    List<Meal> getAllMeals();
}
