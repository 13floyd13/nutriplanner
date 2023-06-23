package com.forrest.nutriplanner.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void getIdUser() {
        user.setIdUser(1L);
        assertEquals(1L, user.getIdUser());
    }

    @Test
    void getLogin() {
        user.setLogin("testLogin");
        assertEquals("testLogin", user.getLogin());
    }

    @Test
    void getPassword() {
        user.setPassword("testPassword");
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    void setIdUser() {
        user.setIdUser(2L);
        assertEquals(2L, user.getIdUser());
    }

    @Test
    void setLogin() {
        user.setLogin("newLogin");
        assertEquals("newLogin", user.getLogin());
    }

    @Test
    void setPassword() {
        user.setPassword("newPassword");
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    void testEquals() {
        User user2 = new User(1L, "testLogin", "testPassword");

        user.setIdUser(1L);
        user.setLogin("testLogin");
        user.setPassword("testPassword");

        assertEquals(user, user2);
    }


    @Test
    void testHashCode() {
        User user2 = new User(1L, "testLogin", "testPassword");

        user.setIdUser(1L);
        user.setLogin("testLogin");
        user.setPassword("testPassword");
        assertEquals(user.hashCode(), user2.hashCode());
    }

    @Test
    void testToString() {
        user.setIdUser(1L);
        user.setLogin("testLogin");
        user.setPassword("testPassword");

        String expectedString = "User(idUser=1, login=testLogin, password=testPassword)";
        assertEquals(expectedString, user.toString());
    }
}