package com.example.demo.demo.repository;

import com.example.demo.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Updated method name
}
