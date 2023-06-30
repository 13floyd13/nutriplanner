package com.forrest.nutriplanner.repository;

import com.forrest.nutriplanner.model.entities.MealFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealFoodRepository extends JpaRepository<MealFood, Long> {
}
