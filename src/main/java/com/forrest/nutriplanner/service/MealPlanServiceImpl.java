package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.entities.MealPlan;
import com.forrest.nutriplanner.repository.MealPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MealPlanServiceImpl implements MealPlanService {
    
    @Autowired
    MealPlanRepository mealPlanRepository;

    @Override
    public MealPlan saveMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    @Override
    public MealPlan updateMealPlan(MealPlan mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    @Override
    public MealPlan getMealPlanById(Long id) {
        Optional<MealPlan> mealPlan = mealPlanRepository.findById(id);

        if (mealPlan.isEmpty()) {
            throw new NoSuchElementException();
        }
        return mealPlan.get();
    }

    @Override
    public void deleteMealPlanById(Long id) {
        mealPlanRepository.deleteById(id);
    }

    @Override
    public List<MealPlan> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }
}
