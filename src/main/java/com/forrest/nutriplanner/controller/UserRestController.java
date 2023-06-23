package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.User;
import com.forrest.nutriplanner.service.UserServiceImpl;
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
@RequestMapping("/api/user")
@Tag(name = "User")
public class UserRestController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/create")
    @Operation(summary = "Create a new user",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                description = "User to create",
                required = true,
                content = @Content(schema = @Schema(implementation = User.class))
    ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource created",
                            content = {@Content(schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            user = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    @Operation(summary = "Update a new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User to update",
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class))
            ))
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Resource updated",
                            content = {@Content(schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        try {
            userService.getUserById(user.getIdUser());
            User newUser = userService.updateUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(newUser);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get User by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "OK",
                            content = {@Content(schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The user's id", name = "idUser", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
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
                            content = {@Content(schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete user by id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "No Content",
                            content = {@Content(schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid input",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Resource not found",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error",
                            content = @Content),
            }
    )
    @Parameter(description = "The user's id", name = "idUser", schema = @Schema(type = "Long"), example = "1")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        try {
            userService.getUserById(id);
            userService.deleteUserById(id);
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
