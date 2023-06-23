package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User updateUser(User user);
    User getUserById(Long id);
    void deleteUserById(Long id);
    List<User> getAllUsers();
}
