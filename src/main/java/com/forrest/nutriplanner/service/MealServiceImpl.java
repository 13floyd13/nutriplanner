package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.Meal;
import com.forrest.nutriplanner.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService{
    @Autowired
    MealRepository mealRepository;

    @Override
    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal updateMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    @Override
    public Meal getMealById(Long id) {
        Optional<Meal> meal = mealRepository.findById(id);

        if (meal.isEmpty()) {
            throw new NoSuchElementException();
        }
        return meal.get();
    }

    @Override
    public void deleteMealById(Long id) {
        mealRepository.deleteById(id);
    }

    @Override
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }
}
