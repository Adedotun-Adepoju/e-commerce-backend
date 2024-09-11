package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateUserDto;
import com.e_commerce.backend.dtos.responses.UserResponseDto;
import com.e_commerce.backend.exceptions.UserNotFoundException;
import com.e_commerce.backend.mappers.UserMapper;
import com.e_commerce.backend.models.User;
import com.e_commerce.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // Define a constant for the repeated error message
    private static final String USER_NOT_FOUND_MESSAGE = "User not found with id: ";

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::convertToUserResponseDto)
                .toList();
    }

    @Override
    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + id));
        return userMapper.convertToUserResponseDto(user);
    }

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.convertToUserEntity(createUserDto);
        User createdUser = userRepository.save(user);
        return userMapper.convertToUserResponseDto(createdUser);
    }

    @Override
    public UserResponseDto updateUser(String id, CreateUserDto createUserDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + id));

        User updatedUser = userMapper.convertToUserEntity(createUserDto);
        updateUserFields(existingUser, updatedUser);

        User savedUser = userRepository.save(existingUser);
        return userMapper.convertToUserResponseDto(savedUser);
    }

    @Override
    public void deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(USER_NOT_FOUND_MESSAGE + id);
        }
    }

    private void updateUserFields(User existingUser, User updatedUser) {
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setCreatedAt(updatedUser.getCreatedAt());
        existingUser.setUpdatedAt(updatedUser.getUpdatedAt());
    }
}
