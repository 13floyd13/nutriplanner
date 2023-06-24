package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.MealPlan;

import java.util.List;

public interface MealPlanService {
    MealPlan saveMealPlan(MealPlan mealPlan);
    MealPlan updateMealPlan(MealPlan mealPlan);
    MealPlan getMealPlanById(Long id);
    void deleteMealPlanById(Long id);
    List<MealPlan> getAllMealPlans();
}
