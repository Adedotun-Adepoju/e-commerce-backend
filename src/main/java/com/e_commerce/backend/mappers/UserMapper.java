package com.e_commerce.backend.mappers;

import com.e_commerce.backend.dtos.requests.CreateUserDto;
import com.e_commerce.backend.dtos.responses.UserResponseDto;
import com.e_commerce.backend.models.User;
import com.e_commerce.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserMapper {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    // Convert User entity to UserResponseDto
    public UserResponseDto convertToUserResponseDto(User user) {
        return UserResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())

                .build();
    }

    // Convert CreateUserDto to User entity
    public User convertToUserEntity(CreateUserDto createUserDto) {
        return User.builder()
                .email(createUserDto.getEmail())
                .name(createUserDto.getName())
                .password(passwordEncoder.encode(createUserDto.getPassword()))
                .phoneNumber(createUserDto.getPhoneNumber())
                .build();
    }
}
