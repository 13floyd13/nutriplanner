package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.entities.Meal;
import com.forrest.nutriplanner.service.MealServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(MealRestController.class)
@ExtendWith(MockitoExtension.class)
class MealRestControllerTest {
    
    @Autowired
    private MealRestController mealRestController;
    
    @MockBean
    private MealServiceImpl mealService;
    
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
    void createMeal() {
        Mockito.when(mealService.saveMeal(meal)).thenReturn(meal);

        ResponseEntity<Meal> response = mealRestController.createMeal(meal);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(meal, response.getBody());
    }

    @Test
    void createMealBadRequest() {
        Mockito.when(mealService.saveMeal(meal)).thenThrow(new IllegalArgumentException());

        ResponseEntity<Meal> response = mealRestController.createMeal(meal);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createMealInternalServerError() {
        Mockito.when(mealService.saveMeal(meal)).thenThrow(new RuntimeException());

        ResponseEntity<Meal> response = mealRestController.createMeal(meal);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateMeal() {
        meal.setDescription("newDescription");
        Mockito.when(mealService.updateMeal(meal)).thenReturn(meal);

        ResponseEntity<Meal> response = mealRestController.updateMeal(meal);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("newDescription", meal.getDescription());

    }

    @Test
    void updateMealBadRequest() {
        Mockito.when(mealService.updateMeal(meal)).thenThrow(new IllegalArgumentException());

        ResponseEntity<Meal> response = mealRestController.updateMeal(meal);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateMealNotFound() {
        Mockito.when(mealService.updateMeal(meal)).thenThrow(new NoSuchElementException());

        ResponseEntity<Meal> response = mealRestController.updateMeal(meal);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateMealInternalServerError() {
        Mockito.when(mealService.updateMeal(meal)).thenThrow(new RuntimeException());

        ResponseEntity<Meal> response = mealRestController.updateMeal(meal);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getMealById() {
        Long id = 1L;
        Mockito.when(mealService.getMealById(id)).thenReturn(meal);

        ResponseEntity<Meal> response = mealRestController.getMealById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meal, response.getBody());
    }

    @Test
    void getMealByIdBadRequest() {
        Long id = 1L;
        Mockito.when(mealService.getMealById(id)).thenThrow(new IllegalArgumentException());

        ResponseEntity<Meal> response = mealRestController.getMealById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getMealByIdNotFound() {
        Long id = 1L;
        Mockito.when(mealService.getMealById(id)).thenThrow(new NoSuchElementException());

        ResponseEntity<Meal> response = mealRestController.getMealById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getMealByIdInternalServerError() {
        Long id = 1L;
        Mockito.when(mealService.getMealById(id)).thenThrow(new RuntimeException());

        ResponseEntity<Meal> response = mealRestController.getMealById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void getAllMeals() {
        Mockito.when(mealService.getAllMeals()).thenReturn(List.of(meal, meal2));

        ResponseEntity<List<Meal>> response = mealRestController.getAllMeals();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(meal, meal2), response.getBody());
    }

    @Test
    void getAllMealsByIdBadRequest() {
        Mockito.when(mealService.getAllMeals()).thenThrow(new IllegalArgumentException());

        ResponseEntity<List<Meal>> response = mealRestController.getAllMeals();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllMealsByIdInternalServerError() {
        Mockito.when(mealService.getAllMeals()).thenThrow(new RuntimeException());

        ResponseEntity<List<Meal>> response = mealRestController.getAllMeals();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deleteMealById() {
        Long id = 1L;
        Mockito.doNothing().when(mealService).deleteMealById(id);

        ResponseEntity<Void> response = mealRestController.deleteMealById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteMealByIdBadRequest() {
        Long id = 1L;
        Mockito.doThrow(new IllegalArgumentException()).when(mealService).deleteMealById(id);

        ResponseEntity<Void> response = mealRestController.deleteMealById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteMealByIdNotFound() {
        Long id = 1L;
        Mockito.doThrow(new NoSuchElementException()).when(mealService).deleteMealById(id);

        ResponseEntity<Void> response = mealRestController.deleteMealById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteMealByIdInternalServerError() {
        Long id = 1L;
        Mockito.doThrow(new RuntimeException()).when(mealService).deleteMealById(id);

        ResponseEntity<Void> response = mealRestController.deleteMealById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}