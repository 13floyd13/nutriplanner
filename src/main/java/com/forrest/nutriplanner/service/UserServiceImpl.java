package com.forrest.nutriplanner.service;

import com.forrest.nutriplanner.model.User;
import com.forrest.nutriplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    /**
     * @param user 
     * @return
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param user 
     * @return
     */
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param id 
     * @return
     */
    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new NoSuchElementException();
        }
        return user.get();
    }

    /**
     * @param id 
     */
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * @return 
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
