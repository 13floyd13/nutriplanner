package com.forrest.nutriplanner.controller;

import com.forrest.nutriplanner.model.entities.User;
import com.forrest.nutriplanner.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Autowired
    private UserRestController userRestController;

    @MockBean
    private UserServiceImpl userService;

    private User user;
    private User user2;

    @BeforeEach
    void setUp() {
        user = new User(1L, "login", "password");
        user2 = new User(2L, "login2", "password2");
    }

    @Test
    void createUser() {
        Mockito.when(userService.saveUser(user)).thenReturn(user);

        ResponseEntity<User> response = userRestController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void createUserBadRequest() {
        Mockito.when(userService.saveUser(user)).thenThrow(new IllegalArgumentException());

        ResponseEntity<User> response = userRestController.createUser(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createUserInternalServerError() {
        Mockito.when(userService.saveUser(user)).thenThrow(new RuntimeException());

        ResponseEntity<User> response = userRestController.createUser(user);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void updateUser() {
        user.setLogin("newLogin");
        Mockito.when(userService.updateUser(user)).thenReturn(user);

        ResponseEntity<User> response = userRestController.updateUser(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("newLogin", user.getLogin());

    }

    @Test
    void updateUserBadRequest() {
        Mockito.when(userService.updateUser(user)).thenThrow(new IllegalArgumentException());

        ResponseEntity<User> response = userRestController.updateUser(user);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void updateUserNotFound() {
        Mockito.when(userService.updateUser(user)).thenThrow(new NoSuchElementException());

        ResponseEntity<User> response = userRestController.updateUser(user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateUserInternalServerError() {
        Mockito.when(userService.updateUser(user)).thenThrow(new RuntimeException());

        ResponseEntity<User> response = userRestController.updateUser(user);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void getUserById() {
        Long id = 1L;
        Mockito.when(userService.getUserById(id)).thenReturn(user);

        ResponseEntity<User> response = userRestController.getUserById(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void getUserByIdBadRequest() {
        Long id = 1L;
        Mockito.when(userService.getUserById(id)).thenThrow(new IllegalArgumentException());

        ResponseEntity<User> response = userRestController.getUserById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getUserByIdNotFound() {
        Long id = 1L;
        Mockito.when(userService.getUserById(id)).thenThrow(new NoSuchElementException());

        ResponseEntity<User> response = userRestController.getUserById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getUserByIdInternalServerError() {
        Long id = 1L;
        Mockito.when(userService.getUserById(id)).thenThrow(new RuntimeException());

        ResponseEntity<User> response = userRestController.getUserById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void getAllUsers() {
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(user, user2));

        ResponseEntity<List<User>> response = userRestController.getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(List.of(user, user2), response.getBody());
    }

    @Test
    void getAllUsersByIdBadRequest() {
        Mockito.when(userService.getAllUsers()).thenThrow(new IllegalArgumentException());

        ResponseEntity<List<User>> response = userRestController.getAllUsers();
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllUsersByIdInternalServerError() {
        Mockito.when(userService.getAllUsers()).thenThrow(new RuntimeException());

        ResponseEntity<List<User>> response = userRestController.getAllUsers();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void deleteUserById() {
        Long id = 1L;
        Mockito.doNothing().when(userService).deleteUserById(id);

        ResponseEntity<Void> response = userRestController.deleteUserById(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteUserByIdBadRequest() {
        Long id = 1L;
        Mockito.doThrow(new IllegalArgumentException()).when(userService).deleteUserById(id);

        ResponseEntity<Void> response = userRestController.deleteUserById(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void deleteUserByIdNotFound() {
        Long id = 1L;
        Mockito.doThrow(new NoSuchElementException()).when(userService).deleteUserById(id);

        ResponseEntity<Void> response = userRestController.deleteUserById(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteUserByIdInternalServerError() {
        Long id = 1L;
        Mockito.doThrow(new RuntimeException()).when(userService).deleteUserById(id);

        ResponseEntity<Void> response = userRestController.deleteUserById(id);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }


}