package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.entities.Food;
import com.forrest.nutriplanner.service.FoodServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/food")
@Tag(name = "Food")
public class FoodRestController {
    
    @Autowired
    FoodServiceImpl foodService;

    @PostMapping("/create")
    @Operation(summary = "Create a new food",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Food to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Food.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource created",
                            content = {@Content(schema = @Schema(implementation = Food.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        try {
            food = foodService.saveFood(food);
            return ResponseEntity.status(HttpStatus.CREATED).body(food);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update a new food",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Food to update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Food.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource updated",
                            content = {@Content(schema = @Schema(implementation = Food.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<Food> updateFood(@RequestBody Food food) {
        try {
            foodService.getFoodById(food.getIdFood());
            Food newFood = foodService.updateFood(food);
            return ResponseEntity.status(HttpStatus.OK).body(newFood);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get Food by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "OK",
                            content = {@Content(schema = @Schema(implementation = Food.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The food's id", name = "idFood", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<Food> getFoodById(@PathVariable("id") Long id) {
        try {
            Food food = foodService.getFoodById(id);
            return ResponseEntity.status(HttpStatus.OK).body(food);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/all")
    @Operation(summary = "Get all clients")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "OK",
                            content = {@Content(schema = @Schema(implementation = Food.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<List<Food>> getAllFoods() {
        try {
            List<Food> foods = foodService.getAllFoods();
            return ResponseEntity.status(HttpStatus.OK).body(foods);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete food by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "No Content",
                            content = {@Content(schema = @Schema(implementation = Food.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The food's id", name = "idFood", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<Void> deleteFoodById(@PathVariable("id") Long id) {
        try {
            foodService.getFoodById(id);
            foodService.deleteFoodById(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
