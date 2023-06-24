package com.forrest.nutriplanner.repository;

import com.forrest.nutriplanner.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
