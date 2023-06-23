package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.User;
import com.forrest.nutriplanner.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @MockBean
    private UserRepository userRepository;

    User user;
    User user2;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setIdUser(1L);
        user.setLogin("login");
        user.setPassword("password");

        user2 = new User(2L, "login2", "password2");
    }

    @Test
    void saveUser() {
        Mockito.when(userRepository.save(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals(user, savedUser);
    }

    @Test
    void updateUser() {
        Mockito.when(userRepository.save(user2)).thenReturn(user2);
        User updatedUser = userService.updateUser(user2);
        assertEquals(user2, updatedUser);
    }

    @Test
    void getUserById() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(user));
        User userGet = userService.getUserById(id);
        assertEquals(user, userGet);
    }

    @Test
    void getUserByIdNoSuchElement() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(id);
        });
    }

    @Test
    void deleteUserById() {
        Long id = 1L;
        Mockito.doNothing().when(userRepository).deleteById(id);

        userService.deleteUserById(id);
        Mockito.verify(userRepository).deleteById(id);

    }

    @Test
    void getAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user, user2));

        List<User> users = userService.getAllUsers();
        assertEquals(2, users.size());
        assertEquals(user, users.get(0));
        assertEquals(user2, users.get(1));
    }
}