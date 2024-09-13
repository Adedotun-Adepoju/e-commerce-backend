package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.dtos.responses.LoginResponseDto;
import com.e_commerce.backend.dtos.responses.UserResponseDto;
import com.e_commerce.backend.dtos.requests.CreateUserDto;
import com.e_commerce.backend.dtos.requests.LoginDto;
import com.e_commerce.backend.mappers.UserMapper;
import com.e_commerce.backend.models.User;
import com.e_commerce.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthenticationService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<UserResponseDto> createUser(CreateUserDto createUserDto) {
        // Find user by email
        User existingUser = userRepository.findByEmail(createUserDto.getEmail())
                .orElse(null);
        if (existingUser != null) {
            // User already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        } else {
            // User does not exist, proceed with registration
            User user = userMapper.convertToUserEntity(createUserDto);
//            user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password
            User createdUser = userRepository.save(user);
            UserResponseDto userResponseDTO = userMapper.convertToUserResponseDto(createdUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDTO);
        }
    }

    @Override
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            User user = userRepository.findByEmail(loginDto.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails);

            LoginResponseDto loginResponse = new LoginResponseDto(loginDto.getEmail(), jwtToken, user.getId());
            ApiResponseDto<LoginResponseDto> response = new ApiResponseDto<>(true, loginResponse, "Login successful", null);
            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            ApiResponseDto<LoginResponseDto> response = new ApiResponseDto<>(false, null, "Authentication failed", HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }



}
