package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.exceptions.DeserializationException;
import com.forrest.nutriplanner.exceptions.HttpRequestException;
import com.forrest.nutriplanner.exceptions.HttpResponseException;
import com.forrest.nutriplanner.exceptions.InvalidBarcodeException;
import com.forrest.nutriplanner.model.entities.Food;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OpenFoodFactsServiceIntegrationTest {

    @Autowired
    private OpenFoodFactsService openFoodFactsService;

    private static final Logger logger = LoggerFactory.getLogger(OpenFoodFactsServiceIntegrationTest.class);
    @Test
    void getFoodByBarCodeTest() {
        String barcode = "3302740059193";

        Food food = new Food();
        try {
            food = openFoodFactsService.getFoodByBarCode(barcode);
        } catch (InvalidBarcodeException | HttpRequestException | HttpResponseException | DeserializationException e) {
            fail("Exception", e);
        }
        assertNotNull(food);
        logger.info(food.toString());
        logger.info("Vitamin-A : " + food.getVitaminA());

    }
}