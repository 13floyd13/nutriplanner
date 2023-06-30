package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.entities.Food;
import com.forrest.nutriplanner.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public Food saveFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food updateFood(Food food) {
        return foodRepository.save(food);
    }

    @Override
    public Food getFoodById(Long id) {
        Optional<Food> food = foodRepository.findById(id);

        if(food.isEmpty()) {
            throw new NoSuchElementException();
        }
        return food.get();
    }

    @Override
    public void deleteFoodById(Long id) {
        foodRepository.deleteById(id);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
