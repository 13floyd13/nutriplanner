package com.forrest.nutriplanner.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forrest.nutriplanner.exceptions.DeserializationException;
import com.forrest.nutriplanner.exceptions.HttpRequestException;
import com.forrest.nutriplanner.exceptions.HttpResponseException;
import com.forrest.nutriplanner.exceptions.InvalidBarcodeException;
import com.forrest.nutriplanner.model.dto.ProductDTO;
import com.forrest.nutriplanner.model.dto.ResponseDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OpenFoodFactsService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private static final String API_URL = "https://world.openfoodfacts.org/api/v2/product/";

    public OpenFoodFactsService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    public ProductDTO getProductByBarCode(String barcode) throws InvalidBarcodeException, HttpRequestException, HttpResponseException, DeserializationException {
        final String function = "getProductByBarCode";
        HttpRequest request;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + barcode))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new InvalidBarcodeException("Invalid Barcode in " + function, e);
        }

        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new HttpRequestException("Error while sending request in " + function, e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new HttpRequestException("Request Interrupted in " + function, e);
        }

        if (response.statusCode() != 200) {
            throw new HttpResponseException(function + " response code not ok " + response.statusCode());
        }

        ResponseDTO responseDTO;
        try {
            responseDTO = objectMapper.readValue(response.body(), ResponseDTO.class);
        } catch (JsonProcessingException e) {
            throw new DeserializationException("Error while deserialize in " + function, e);
        }
        return responseDTO.getProduct();

    }

}
