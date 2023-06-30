package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.entities.MealPlan;
import com.forrest.nutriplanner.service.MealPlanServiceImpl;
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
@WebMvcTest(MealPlanRestController.class)
@ExtendWith(MockitoExtension.class)
class MealPlanRestControllerTest {
    
    @Autowired
    private MealPlanRestController mealPlanRestController;
    
    @MockBean
    private MealPlanServiceImpl mealPlanService;
    private MealPlan mealPlan;
    private MealPlan mealPlan2;

    @BeforeEach
    void setUp() {
        mealPlan = new MealPlan();
        mealPlan.setIdMealPlan(1L);
        mealPlan2 = new MealPlan();
        mealPlan2.setIdMealPlan(2L);
    }

    @Test
    void createMealPlan() {
        Mockito.when(mealPlanService.saveMealPlan(mealPlan)).thenReturn(mealPlan);

        ResponseEntity<MealPlan> response = mealPlanRestController.createMealPlan(mealPlan);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mealPlan, response.getBody());
    }

    @Test
    void createMealPlanBadRequest() {
        Mockito.when(mealPlanService.saveMealPlan(mealPlan)).thenThrow(new IllegalArgumentException());

        ResponseEntity<MealPlan> response = mealPlanRestController.createMealPlan(mealPlan);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createMealPlanInternalServerError() {
        Mockito.when(mealPlanService.saveMealPlan(mealPlan)).thenThrow(new RuntimeException());

        ResponseEntity<MealPlan> response = mealPlanRestController.createMealPlan(mealPlan);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateMealPlan() {
        mealPlan.setDescription("newDescription");
        Mockito.when(mealPlanService.updateMealPlan(mealPlan)).thenReturn(mealPlan);

        ResponseEntity<MealPlan> response = mealPlanRestController.updateMealPlan(mealPlan);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("newDescription", mealPlan.getDescription());

    }

    @Test
    void updateMealPlanBadRequest() {
        Mockito.when(mealPlanService.updateMealPlan(mealPlan)).thenThrow(new IllegalArgumentException());

        ResponseEntity<MealPlan> response = mealPlanRestController.updateMealPlan(mealPlan);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateMealPlanNotFound() {
        Mockito.when(mealPlanService.updateMealPlan(mealPlan)).thenThrow(new NoSuchElementException());

        ResponseEntity<MealPlan> response = mealPlanRestController.updateMealPlan(mealPlan);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateMealPlanInternalServerError() {
        Mockito.when(mealPlanService.updateMealPlan(mealPlan)).thenThrow(new RuntimeException());

        ResponseEntity<MealPlan> response = mealPlanRestController.updateMealPlan(mealPlan);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getMealPlanById() {
        Long id = 1L;
        Mockito.when(mealPlanService.getMealPlanById(id)).thenReturn(mealPlan);

        ResponseEntity<MealPlan> response = mealPlanRestController.getMealPlanById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mealPlan, response.getBody());
    }

    @Test
    void getMealPlanByIdBadRequest() {
        Long id = 1L;
        Mockito.when(mealPlanService.getMealPlanById(id)).thenThrow(new IllegalArgumentException());

        ResponseEntity<MealPlan> response = mealPlanRestController.getMealPlanById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getMealPlanByIdNotFound() {
        Long id = 1L;
        Mockito.when(mealPlanService.getMealPlanById(id)).thenThrow(new NoSuchElementException());

        ResponseEntity<MealPlan> response = mealPlanRestController.getMealPlanById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getMealPlanByIdInternalServerError() {
        Long id = 1L;
        Mockito.when(mealPlanService.getMealPlanById(id)).thenThrow(new RuntimeException());

        ResponseEntity<MealPlan> response = mealPlanRestController.getMealPlanById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void getAllMealPlans() {
        Mockito.when(mealPlanService.getAllMealPlans()).thenReturn(List.of(mealPlan, mealPlan2));

        ResponseEntity<List<MealPlan>> response = mealPlanRestController.getAllMealPlans();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(mealPlan, mealPlan2), response.getBody());
    }

    @Test
    void getAllMealPlansByIdBadRequest() {
        Mockito.when(mealPlanService.getAllMealPlans()).thenThrow(new IllegalArgumentException());

        ResponseEntity<List<MealPlan>> response = mealPlanRestController.getAllMealPlans();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllMealPlansByIdInternalServerError() {
        Mockito.when(mealPlanService.getAllMealPlans()).thenThrow(new RuntimeException());

        ResponseEntity<List<MealPlan>> response = mealPlanRestController.getAllMealPlans();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deleteMealPlanById() {
        Long id = 1L;
        Mockito.doNothing().when(mealPlanService).deleteMealPlanById(id);

        ResponseEntity<Void> response = mealPlanRestController.deleteMealPlanById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteMealPlanByIdBadRequest() {
        Long id = 1L;
        Mockito.doThrow(new IllegalArgumentException()).when(mealPlanService).deleteMealPlanById(id);

        ResponseEntity<Void> response = mealPlanRestController.deleteMealPlanById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteMealPlanByIdNotFound() {
        Long id = 1L;
        Mockito.doThrow(new NoSuchElementException()).when(mealPlanService).deleteMealPlanById(id);

        ResponseEntity<Void> response = mealPlanRestController.deleteMealPlanById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteMealPlanByIdInternalServerError() {
        Long id = 1L;
        Mockito.doThrow(new RuntimeException()).when(mealPlanService).deleteMealPlanById(id);

        ResponseEntity<Void> response = mealPlanRestController.deleteMealPlanById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}