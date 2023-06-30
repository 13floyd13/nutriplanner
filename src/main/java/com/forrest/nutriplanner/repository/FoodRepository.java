package com.forrest.nutriplanner.repository;

import com.forrest.nutriplanner.model.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
