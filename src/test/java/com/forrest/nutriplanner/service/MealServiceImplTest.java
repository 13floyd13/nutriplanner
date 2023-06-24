package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.Meal;
import com.forrest.nutriplanner.repository.MealRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MealServiceImplTest {

    @Autowired
    private MealServiceImpl mealService;
    
    @MockBean
    private MealRepository mealRepository;
    
    private Meal meal;
    private Meal meal2;
    @BeforeEach
    void setUp() {
        meal = new Meal();
        meal.setIdMeal(1L);

        meal2 = new Meal();
        meal2.setIdMeal(2L);
    }

    @Test
    void saveMeal() {
        Mockito.when(mealRepository.save(meal)).thenReturn(meal);
        Meal savedMeal = mealService.saveMeal(meal);
        assertEquals(meal, savedMeal);
    }

    @Test
    void updateMeal() {
        Mockito.when(mealRepository.save(meal2)).thenReturn(meal2);
        Meal updatedMeal = mealService.updateMeal(meal2);
        assertEquals(meal2, updatedMeal);
    }

    @Test
    void getMealById() {
        Long id = 1L;
        Mockito.when(mealRepository.findById(id)).thenReturn(Optional.of(meal));
        Meal mealGet = mealService.getMealById(id);
        assertEquals(meal, mealGet);
    }

    @Test
    void getMealByIdNoSuchElement() {
        Long id = 1L;
        Mockito.when(mealRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> mealService.getMealById(id));
    }

    @Test
    void deleteMealById() {
        Long id = 1L;
        Mockito.doNothing().when(mealRepository).deleteById(id);

        mealService.deleteMealById(id);
        Mockito.verify(mealRepository).deleteById(id);

    }

    @Test
    void getAllMeals() {
        Mockito.when(mealRepository.findAll()).thenReturn(List.of(meal, meal2));

        List<Meal> meals = mealService.getAllMeals();
        assertEquals(2, meals.size());
        assertEquals(meal, meals.get(0));
        assertEquals(meal2, meals.get(1));
    }
}