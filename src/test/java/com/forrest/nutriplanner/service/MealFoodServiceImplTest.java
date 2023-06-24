package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.MealFood;
import com.forrest.nutriplanner.repository.MealFoodRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MealFoodServiceImplTest {
    
    @Autowired
    private MealFoodServiceImpl mealFoodService;
    @MockBean
    private MealFoodRepository mealFoodRepository;
    private MealFood mealFood;
    private MealFood mealFood2;

    @BeforeEach
    void setUp() {
        mealFood = new MealFood();
        mealFood.setIdMealFood(1L);
        mealFood2 = new MealFood();
        mealFood2.setIdMealFood(2L);
    }

    @Test
    void saveMealFood() {
        Mockito.when(mealFoodRepository.save(mealFood)).thenReturn(mealFood);
        MealFood savedMealFood = mealFoodService.saveMealFood(mealFood);
        assertEquals(mealFood, savedMealFood);
    }

    @Test
    void updateMealFood() {
        Mockito.when(mealFoodRepository.save(mealFood2)).thenReturn(mealFood2);
        MealFood updatedMealFood = mealFoodService.updateMealFood(mealFood2);
        assertEquals(mealFood2, updatedMealFood);
    }

    @Test
    void getMealFoodById() {
        Long id = 1L;
        Mockito.when(mealFoodRepository.findById(id)).thenReturn(Optional.of(mealFood));
        MealFood mealFoodGet = mealFoodService.getMealFoodById(id);
        assertEquals(mealFood, mealFoodGet);
    }

    @Test
    void getMealFoodByIdNoSuchElement() {
        Long id = 1L;
        Mockito.when(mealFoodRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> mealFoodService.getMealFoodById(id));
    }

    @Test
    void deleteMealFoodById() {
        Long id = 1L;
        Mockito.doNothing().when(mealFoodRepository).deleteById(id);

        mealFoodService.deleteMealFoodById(id);
        Mockito.verify(mealFoodRepository).deleteById(id);

    }

    @Test
    void getAllMealFoods() {
        Mockito.when(mealFoodRepository.findAll()).thenReturn(List.of(mealFood, mealFood2));

        List<MealFood> mealFoods = mealFoodService.getAllMealFoods();
        assertEquals(2, mealFoods.size());
        assertEquals(mealFood, mealFoods.get(0));
        assertEquals(mealFood2, mealFoods.get(1));
    }
}