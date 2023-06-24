package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.MealFood;
import com.forrest.nutriplanner.repository.MealFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MealFoodServiceImpl implements MealFoodService{
    
    @Autowired
    MealFoodRepository mealFoodRepository;

    @Override
    public MealFood saveMealFood(MealFood mealFood) {
        return mealFoodRepository.save(mealFood);
    }

    @Override
    public MealFood updateMealFood(MealFood mealFood) {
        return mealFoodRepository.save(mealFood);
    }

    @Override
    public MealFood getMealFoodById(Long id) {
        Optional<MealFood> mealFood = mealFoodRepository.findById(id);

        if (mealFood.isEmpty()) {
            throw new NoSuchElementException();
        }
        return mealFood.get();
    }

    @Override
    public void deleteMealFoodById(Long id) {
        mealFoodRepository.deleteById(id);
    }

    @Override
    public List<MealFood> getAllMealFoods() {
        return mealFoodRepository.findAll();
    }
}
