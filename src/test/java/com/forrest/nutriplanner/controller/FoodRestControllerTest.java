package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.entities.Food;
import com.forrest.nutriplanner.service.FoodServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class FoodRestControllerTest {
    
    @Autowired
    private FoodRestController foodRestController;
    
    @MockBean
    private FoodServiceImpl foodService;
    
    private Food food;
    private Food food2;

    @BeforeEach
    void setUp() {
        food = new Food();
        food.setIdFood(1L);
        
        food2 = new Food();
        food2.setIdFood(1L);
    }

    @Test
    void createFood() {
        Mockito.when(foodService.saveFood(food)).thenReturn(food);

        ResponseEntity<Food> response = foodRestController.createFood(food);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(food, response.getBody());
    }

    @Test
    void createFoodBadRequest() {
        Mockito.when(foodService.saveFood(food)).thenThrow(new IllegalArgumentException());

        ResponseEntity<Food> response = foodRestController.createFood(food);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createFoodInternalServerError() {
        Mockito.when(foodService.saveFood(food)).thenThrow(new RuntimeException());

        ResponseEntity<Food> response = foodRestController.createFood(food);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateFood() {
        food.setBrand("newBrand");
        Mockito.when(foodService.updateFood(food)).thenReturn(food);

        ResponseEntity<Food> response = foodRestController.updateFood(food);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("newBrand", food.getBrand());

    }

    @Test
    void updateFoodBadRequest() {
        Mockito.when(foodService.updateFood(food)).thenThrow(new IllegalArgumentException());

        ResponseEntity<Food> response = foodRestController.updateFood(food);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateFoodNotFound() {
        Mockito.when(foodService.updateFood(food)).thenThrow(new NoSuchElementException());

        ResponseEntity<Food> response = foodRestController.updateFood(food);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateFoodInternalServerError() {
        Mockito.when(foodService.updateFood(food)).thenThrow(new RuntimeException());

        ResponseEntity<Food> response = foodRestController.updateFood(food);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getFoodById() {
        Long id = 1L;
        Mockito.when(foodService.getFoodById(id)).thenReturn(food);

        ResponseEntity<Food> response = foodRestController.getFoodById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(food, response.getBody());
    }

    @Test
    void getFoodByIdBadRequest() {
        Long id = 1L;
        Mockito.when(foodService.getFoodById(id)).thenThrow(new IllegalArgumentException());

        ResponseEntity<Food> response = foodRestController.getFoodById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getFoodByIdNotFound() {
        Long id = 1L;
        Mockito.when(foodService.getFoodById(id)).thenThrow(new NoSuchElementException());

        ResponseEntity<Food> response = foodRestController.getFoodById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getFoodByIdInternalServerError() {
        Long id = 1L;
        Mockito.when(foodService.getFoodById(id)).thenThrow(new RuntimeException());

        ResponseEntity<Food> response = foodRestController.getFoodById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void getAllFoods() {
        Mockito.when(foodService.getAllFoods()).thenReturn(List.of(food, food2));

        ResponseEntity<List<Food>> response = foodRestController.getAllFoods();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(food, food2), response.getBody());
    }

    @Test
    void getAllFoodsByIdBadRequest() {
        Mockito.when(foodService.getAllFoods()).thenThrow(new IllegalArgumentException());

        ResponseEntity<List<Food>> response = foodRestController.getAllFoods();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllFoodsByIdInternalServerError() {
        Mockito.when(foodService.getAllFoods()).thenThrow(new RuntimeException());

        ResponseEntity<List<Food>> response = foodRestController.getAllFoods();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deleteFoodById() {
        Long id = 1L;
        Mockito.doNothing().when(foodService).deleteFoodById(id);

        ResponseEntity<Void> response = foodRestController.deleteFoodById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteFoodByIdBadRequest() {
        Long id = 1L;
        Mockito.doThrow(new IllegalArgumentException()).when(foodService).deleteFoodById(id);

        ResponseEntity<Void> response = foodRestController.deleteFoodById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteFoodByIdNotFound() {
        Long id = 1L;
        Mockito.doThrow(new NoSuchElementException()).when(foodService).deleteFoodById(id);

        ResponseEntity<Void> response = foodRestController.deleteFoodById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteFoodByIdInternalServerError() {
        Long id = 1L;
        Mockito.doThrow(new RuntimeException()).when(foodService).deleteFoodById(id);

        ResponseEntity<Void> response = foodRestController.deleteFoodById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}