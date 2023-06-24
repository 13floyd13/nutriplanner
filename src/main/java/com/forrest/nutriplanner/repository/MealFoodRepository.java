package com.forrest.nutriplanner.repository;

import com.forrest.nutriplanner.model.MealFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealFoodRepository extends JpaRepository<MealFood, Long> {
}
