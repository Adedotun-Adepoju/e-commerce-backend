package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.responses.LoginResponseDto;
import com.e_commerce.backend.dtos.responses.UserResponseDto;
import com.e_commerce.backend.dtos.requests.CreateUserDto;
import com.e_commerce.backend.dtos.requests.LoginDto;
import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    ResponseEntity<UserResponseDto> createUser(CreateUserDto createUserDto);
    ResponseEntity<ApiResponseDto<LoginResponseDto>> login(LoginDto loginDto);
}
