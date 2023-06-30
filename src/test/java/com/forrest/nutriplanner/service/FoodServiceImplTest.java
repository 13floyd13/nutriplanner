package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.entities.Food;
import com.forrest.nutriplanner.repository.FoodRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(FoodServiceImpl.class)
@ExtendWith(MockitoExtension.class)
class FoodServiceImplTest {

    @Autowired
    private FoodServiceImpl foodService;
    
    @MockBean
    private FoodRepository foodRepository;
    
    Food food;
    Food food2;
    @BeforeEach
    void setUp() {
        food = new Food();
        food.setIdFood(1L);
        
        food2 = new Food();
        food2.setIdFood(2L);
    }

    @Test
    void saveFood() {
        Mockito.when(foodRepository.save(food)).thenReturn(food);
        Food savedFood = foodService.saveFood(food);
        assertEquals(food, savedFood);
    }

    @Test
    void updateFood() {
        Mockito.when(foodRepository.save(food2)).thenReturn(food2);
        Food updatedFood = foodService.updateFood(food2);
        assertEquals(food2, updatedFood);
    }

    @Test
    void getFoodById() {
        Long id = 1L;
        Mockito.when(foodRepository.findById(id)).thenReturn(Optional.of(food));
        Food foodGet = foodService.getFoodById(id);
        assertEquals(food, foodGet);
    }

    @Test
    void getFoodByIdNoSuchElement() {
        Long id = 1L;
        Mockito.when(foodRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> foodService.getFoodById(id));
    }

    @Test
    void deleteFoodById() {
        Long id = 1L;
        Mockito.doNothing().when(foodRepository).deleteById(id);

        foodService.deleteFoodById(id);
        Mockito.verify(foodRepository).deleteById(id);

    }

    @Test
    void getAllFoods() {
        Mockito.when(foodRepository.findAll()).thenReturn(List.of(food, food2));

        List<Food> foods = foodService.getAllFoods();
        assertEquals(2, foods.size());
        assertEquals(food, foods.get(0));
        assertEquals(food2, foods.get(1));
    }
}