package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.MealFood;
import com.forrest.nutriplanner.service.MealFoodServiceImpl;
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
@RequestMapping("/api/mealFood")
@Tag(name = "MealFood")
public class MealFoodRestController {
    
    @Autowired
    MealFoodServiceImpl mealFoodService;

    @PostMapping("/create")
    @Operation(summary = "Create a new mealFood",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "MealFood to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MealFood.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource created",
                            content = {@Content(schema = @Schema(implementation = MealFood.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<MealFood> createMealFood(@RequestBody MealFood mealFood) {
        try {
            mealFood = mealFoodService.saveMealFood(mealFood);
            return ResponseEntity.status(HttpStatus.CREATED).body(mealFood);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update a new mealFood",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "MealFood to update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MealFood.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource updated",
                            content = {@Content(schema = @Schema(implementation = MealFood.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<MealFood> updateMealFood(@RequestBody MealFood mealFood) {
        try {
            mealFoodService.getMealFoodById(mealFood.getIdMealFood());
            MealFood newMealFood = mealFoodService.updateMealFood(mealFood);
            return ResponseEntity.status(HttpStatus.OK).body(newMealFood);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get MealFood by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "OK",
                            content = {@Content(schema = @Schema(implementation = MealFood.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The mealFood's id", name = "idMealFood", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<MealFood> getMealFoodById(@PathVariable("id") Long id) {
        try {
            MealFood mealFood = mealFoodService.getMealFoodById(id);
            return ResponseEntity.status(HttpStatus.OK).body(mealFood);
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
                            content = {@Content(schema = @Schema(implementation = MealFood.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<List<MealFood>> getAllMealFoods() {
        try {
            List<MealFood> mealFoods = mealFoodService.getAllMealFoods();
            return ResponseEntity.status(HttpStatus.OK).body(mealFoods);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete mealFood by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "No Content",
                            content = {@Content(schema = @Schema(implementation = MealFood.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The mealFood's id", name = "idMealFood", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<Void> deleteMealFoodById(@PathVariable("id") Long id) {
        try {
            mealFoodService.getMealFoodById(id);
            mealFoodService.deleteMealFoodById(id);
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
