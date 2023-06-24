package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.Food;

import java.util.List;

public interface FoodService {
    Food saveFood(Food food);
    Food updateFood(Food food);
    Food getFoodById(Long id);
    void deleteFoodById(Long id);
    List<Food> getAllFoods();
}
