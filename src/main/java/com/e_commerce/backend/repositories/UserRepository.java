package com.e_commerce.backend.repositories;

import com.e_commerce.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional <User> findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
    boolean existsByUserId(String userId);
}