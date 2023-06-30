package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.entities.MealPlan;
import com.forrest.nutriplanner.repository.MealPlanRepository;
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
@WebMvcTest(MealPlanServiceImpl.class)
@ExtendWith(MockitoExtension.class)
class MealPlanServiceImplTest {
    
    @Autowired
    private MealPlanServiceImpl mealPlanService;
    
    @MockBean
    private MealPlanRepository mealPlanRepository;
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
    void saveMealPlan() {
        Mockito.when(mealPlanRepository.save(mealPlan)).thenReturn(mealPlan);
        MealPlan savedMealPlan = mealPlanService.saveMealPlan(mealPlan);
        assertEquals(mealPlan, savedMealPlan);
    }

    @Test
    void updateMealPlan() {
        Mockito.when(mealPlanRepository.save(mealPlan2)).thenReturn(mealPlan2);
        MealPlan updatedMealPlan = mealPlanService.updateMealPlan(mealPlan2);
        assertEquals(mealPlan2, updatedMealPlan);
    }

    @Test
    void getMealPlanById() {
        Long id = 1L;
        Mockito.when(mealPlanRepository.findById(id)).thenReturn(Optional.of(mealPlan));
        MealPlan mealPlanGet = mealPlanService.getMealPlanById(id);
        assertEquals(mealPlan, mealPlanGet);
    }

    @Test
    void getMealPlanByIdNoSuchElement() {
        Long id = 1L;
        Mockito.when(mealPlanRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> mealPlanService.getMealPlanById(id));
    }

    @Test
    void deleteMealPlanById() {
        Long id = 1L;
        Mockito.doNothing().when(mealPlanRepository).deleteById(id);

        mealPlanService.deleteMealPlanById(id);
        Mockito.verify(mealPlanRepository).deleteById(id);

    }

    @Test
    void getAllMealPlans() {
        Mockito.when(mealPlanRepository.findAll()).thenReturn(List.of(mealPlan, mealPlan2));

        List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
        assertEquals(2, mealPlans.size());
        assertEquals(mealPlan, mealPlans.get(0));
        assertEquals(mealPlan2, mealPlans.get(1));
    }
}