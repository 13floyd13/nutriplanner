package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.exceptions.DeserializationException;
import com.forrest.nutriplanner.exceptions.HttpRequestException;
import com.forrest.nutriplanner.exceptions.HttpResponseException;
import com.forrest.nutriplanner.exceptions.InvalidBarcodeException;
import com.forrest.nutriplanner.model.dto.ProductDTO;
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
    void getProductByBarCodeTest() {
        String barcode = "3302740059193";

        ProductDTO product = null;
        try {
            product = openFoodFactsService.getProductByBarCode(barcode);
        } catch (InvalidBarcodeException | HttpRequestException | HttpResponseException | DeserializationException e) {
            fail("Exception", e);
        }
        assertNotNull(product);
        logger.info(product.toString());

    }
}