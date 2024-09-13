package com.e_commerce.backend.controllers;

import com.e_commerce.backend.dtos.requests.CreateUserDto;
import com.e_commerce.backend.dtos.responses.UserResponseDto;
import com.e_commerce.backend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Retrieve all users.
     *
     * @return List of UserResponseDto
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Retrieve a user by ID.
     *
     * @param id User ID
     * @return UserResponseDto
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }



    /**
     * Update an existing user by ID.
     *
     * @param id             User ID
     * @param createUserDTO Data Transfer Object for updating user
     * @return Updated UserResponseDto
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable String id, @RequestBody CreateUserDto createUserDTO) {
        UserResponseDto updatedUser = userService.updateUser(id, createUserDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Delete a user by ID.
     *
     * @param id User ID
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
