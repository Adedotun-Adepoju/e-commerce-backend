package com.e_commerce.backend.controllers;

import com.e_commerce.backend.dtos.requests.CreateUserDto;
import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.dtos.responses.LoginResponseDto;
import com.e_commerce.backend.dtos.responses.UserResponseDto;
import com.e_commerce.backend.services.UserService;
import com.e_commerce.backend.services.AuthenticationService;
import com.e_commerce.backend.dtos.requests.LoginDto;
import com.e_commerce.backend.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/register")
    public ApiResponseDto<UserResponseDto> createUser(@RequestBody CreateUserDto createUserDTO) {
        userService.createUser(createUserDTO);
        return ResponseUtil.success(null, "Registration successful");
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(@RequestBody LoginDto loginDto) {
        return authenticationService.login(loginDto);
    }
}
