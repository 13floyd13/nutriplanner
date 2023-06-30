package com.forrest.nutriplanner.repository;

import com.forrest.nutriplanner.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
