package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.entities.Meal;
import com.forrest.nutriplanner.service.MealServiceImpl;
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
@RequestMapping("/api/meal")
@Tag(name = "Meal")
public class MealRestController {
    
    @Autowired
    MealServiceImpl mealService;

    @PostMapping("/create")
    @Operation(summary = "Create a new meal",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Meal to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Meal.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource created",
                            content = {@Content(schema = @Schema(implementation = Meal.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<Meal> createMeal(@RequestBody Meal meal) {
        try {
            meal = mealService.saveMeal(meal);
            return ResponseEntity.status(HttpStatus.CREATED).body(meal);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update a new meal",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Meal to update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Meal.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource updated",
                            content = {@Content(schema = @Schema(implementation = Meal.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<Meal> updateMeal(@RequestBody Meal meal) {
        try {
            mealService.getMealById(meal.getIdMeal());
            Meal newMeal = mealService.updateMeal(meal);
            return ResponseEntity.status(HttpStatus.OK).body(newMeal);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get Meal by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "OK",
                            content = {@Content(schema = @Schema(implementation = Meal.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The meal's id", name = "idMeal", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<Meal> getMealById(@PathVariable("id") Long id) {
        try {
            Meal meal = mealService.getMealById(id);
            return ResponseEntity.status(HttpStatus.OK).body(meal);
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
                            content = {@Content(schema = @Schema(implementation = Meal.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<List<Meal>> getAllMeals() {
        try {
            List<Meal> meals = mealService.getAllMeals();
            return ResponseEntity.status(HttpStatus.OK).body(meals);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete meal by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "No Content",
                            content = {@Content(schema = @Schema(implementation = Meal.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The meal's id", name = "idMeal", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<Void> deleteMealById(@PathVariable("id") Long id) {
        try {
            mealService.getMealById(id);
            mealService.deleteMealById(id);
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
