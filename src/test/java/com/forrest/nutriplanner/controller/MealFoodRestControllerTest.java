package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.MealFood;
import com.forrest.nutriplanner.service.MealFoodServiceImpl;
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
class MealFoodRestControllerTest {

    @Autowired
    private MealFoodRestController mealFoodRestController;
    
    @MockBean
    private MealFoodServiceImpl mealFoodService;
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
    void createMealFood() {
        Mockito.when(mealFoodService.saveMealFood(mealFood)).thenReturn(mealFood);

        ResponseEntity<MealFood> response = mealFoodRestController.createMealFood(mealFood);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mealFood, response.getBody());
    }

    @Test
    void createMealFoodBadRequest() {
        Mockito.when(mealFoodService.saveMealFood(mealFood)).thenThrow(new IllegalArgumentException());

        ResponseEntity<MealFood> response = mealFoodRestController.createMealFood(mealFood);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createMealFoodInternalServerError() {
        Mockito.when(mealFoodService.saveMealFood(mealFood)).thenThrow(new RuntimeException());

        ResponseEntity<MealFood> response = mealFoodRestController.createMealFood(mealFood);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateMealFood() {
        mealFood.setQuantity(5);
        Mockito.when(mealFoodService.updateMealFood(mealFood)).thenReturn(mealFood);

        ResponseEntity<MealFood> response = mealFoodRestController.updateMealFood(mealFood);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(5, mealFood.getQuantity());

    }

    @Test
    void updateMealFoodBadRequest() {
        Mockito.when(mealFoodService.updateMealFood(mealFood)).thenThrow(new IllegalArgumentException());

        ResponseEntity<MealFood> response = mealFoodRestController.updateMealFood(mealFood);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateMealFoodNotFound() {
        Mockito.when(mealFoodService.updateMealFood(mealFood)).thenThrow(new NoSuchElementException());

        ResponseEntity<MealFood> response = mealFoodRestController.updateMealFood(mealFood);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateMealFoodInternalServerError() {
        Mockito.when(mealFoodService.updateMealFood(mealFood)).thenThrow(new RuntimeException());

        ResponseEntity<MealFood> response = mealFoodRestController.updateMealFood(mealFood);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getMealFoodById() {
        Long id = 1L;
        Mockito.when(mealFoodService.getMealFoodById(id)).thenReturn(mealFood);

        ResponseEntity<MealFood> response = mealFoodRestController.getMealFoodById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mealFood, response.getBody());
    }

    @Test
    void getMealFoodByIdBadRequest() {
        Long id = 1L;
        Mockito.when(mealFoodService.getMealFoodById(id)).thenThrow(new IllegalArgumentException());

        ResponseEntity<MealFood> response = mealFoodRestController.getMealFoodById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getMealFoodByIdNotFound() {
        Long id = 1L;
        Mockito.when(mealFoodService.getMealFoodById(id)).thenThrow(new NoSuchElementException());

        ResponseEntity<MealFood> response = mealFoodRestController.getMealFoodById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getMealFoodByIdInternalServerError() {
        Long id = 1L;
        Mockito.when(mealFoodService.getMealFoodById(id)).thenThrow(new RuntimeException());

        ResponseEntity<MealFood> response = mealFoodRestController.getMealFoodById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void getAllMealFoods() {
        Mockito.when(mealFoodService.getAllMealFoods()).thenReturn(List.of(mealFood, mealFood2));

        ResponseEntity<List<MealFood>> response = mealFoodRestController.getAllMealFoods();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(mealFood, mealFood2), response.getBody());
    }

    @Test
    void getAllMealFoodsByIdBadRequest() {
        Mockito.when(mealFoodService.getAllMealFoods()).thenThrow(new IllegalArgumentException());

        ResponseEntity<List<MealFood>> response = mealFoodRestController.getAllMealFoods();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllMealFoodsByIdInternalServerError() {
        Mockito.when(mealFoodService.getAllMealFoods()).thenThrow(new RuntimeException());

        ResponseEntity<List<MealFood>> response = mealFoodRestController.getAllMealFoods();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deleteMealFoodById() {
        Long id = 1L;
        Mockito.doNothing().when(mealFoodService).deleteMealFoodById(id);

        ResponseEntity<Void> response = mealFoodRestController.deleteMealFoodById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteMealFoodByIdBadRequest() {
        Long id = 1L;
        Mockito.doThrow(new IllegalArgumentException()).when(mealFoodService).deleteMealFoodById(id);

        ResponseEntity<Void> response = mealFoodRestController.deleteMealFoodById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteMealFoodByIdNotFound() {
        Long id = 1L;
        Mockito.doThrow(new NoSuchElementException()).when(mealFoodService).deleteMealFoodById(id);

        ResponseEntity<Void> response = mealFoodRestController.deleteMealFoodById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteMealFoodByIdInternalServerError() {
        Long id = 1L;
        Mockito.doThrow(new RuntimeException()).when(mealFoodService).deleteMealFoodById(id);

        ResponseEntity<Void> response = mealFoodRestController.deleteMealFoodById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}