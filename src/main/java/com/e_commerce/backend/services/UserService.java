package com.e_commerce.backend.services;
import com.e_commerce.backend.dtos.responses.UserResponseDto;
import com.e_commerce.backend.dtos.requests.CreateUserDto;
import java.util.List;

public interface UserService {
    /**
     * Retrieve all users and map them to UserResponseDTO.
     *
     * @return List of UserResponseDTO
     */
    List<UserResponseDto> getAllUsers();

    /**
     * Retrieve a user by ID and map to UserResponseDTO.
     *
     * @param id User ID
     * @return UserResponseDTO
     */
    UserResponseDto getUserById(String id);

    /**
     * Create a new user from CreateUserDTO and return UserResponseDTO.
     *
     * @param createUserDto Data Transfer Object for user creation
     * @return UserResponseDTO
     */
    UserResponseDto createUser(CreateUserDto createUserDto);

    /**
     * Update an existing user by ID using CreateUserDTO and return updated UserResponseDTO.
     *
     * @param id             User ID
     * @param createUserDto Data Transfer Object for updating user
     * @return Updated UserResponseDTO
     */
    UserResponseDto updateUser(String id, CreateUserDto createUserDto);

    /**
     * Delete a user by ID.
     *
     * @param id User ID
     */
    void deleteUser(String id);
}
