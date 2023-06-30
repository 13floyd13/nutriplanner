package com.forrest.nutriplanner.repository;

import com.forrest.nutriplanner.model.entities.MealPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {
}
