package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.entities.MealPlan;
import com.forrest.nutriplanner.service.MealPlanServiceImpl;
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
@RequestMapping("/api/mealPlan")
@Tag(name = "MealPlan")
public class MealPlanRestController {
    
    @Autowired
    MealPlanServiceImpl mealPlanService;

    @PostMapping("/create")
    @Operation(summary = "Create a new mealPlan",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "MealPlan to create",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MealPlan.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource created",
                            content = {@Content(schema = @Schema(implementation = MealPlan.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<MealPlan> createMealPlan(@RequestBody MealPlan mealPlan) {
        try {
            mealPlan = mealPlanService.saveMealPlan(mealPlan);
            return ResponseEntity.status(HttpStatus.CREATED).body(mealPlan);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update a new mealPlan",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "MealPlan to update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MealPlan.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource updated",
                            content = {@Content(schema = @Schema(implementation = MealPlan.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<MealPlan> updateMealPlan(@RequestBody MealPlan mealPlan) {
        try {
            mealPlanService.getMealPlanById(mealPlan.getIdMealPlan());
            MealPlan newMealPlan = mealPlanService.updateMealPlan(mealPlan);
            return ResponseEntity.status(HttpStatus.OK).body(newMealPlan);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get MealPlan by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "OK",
                            content = {@Content(schema = @Schema(implementation = MealPlan.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The mealPlan's id", name = "idMealPlan", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<MealPlan> getMealPlanById(@PathVariable("id") Long id) {
        try {
            MealPlan mealPlan = mealPlanService.getMealPlanById(id);
            return ResponseEntity.status(HttpStatus.OK).body(mealPlan);
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
                            content = {@Content(schema = @Schema(implementation = MealPlan.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<List<MealPlan>> getAllMealPlans() {
        try {
            List<MealPlan> mealPlans = mealPlanService.getAllMealPlans();
            return ResponseEntity.status(HttpStatus.OK).body(mealPlans);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete mealPlan by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "No Content",
                            content = {@Content(schema = @Schema(implementation = MealPlan.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The mealPlan's id", name = "idMealPlan", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<Void> deleteMealPlanById(@PathVariable("id") Long id) {
        try {
            mealPlanService.getMealPlanById(id);
            mealPlanService.deleteMealPlanById(id);
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
