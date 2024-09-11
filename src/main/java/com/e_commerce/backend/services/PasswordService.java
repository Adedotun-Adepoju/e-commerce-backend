package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.exceptions.UserNotFoundException;
import com.e_commerce.backend.models.User;
import com.e_commerce.backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Value("${otp.expiration.time}") // Configured in application.properties
    private long otpExpirationTimeInMinutes;

    // Define a constant for the repeated error message
    private static final String USER_NOT_FOUND_MESSAGE = "User not found with email: ";

    // Create a single Random instance and reuse it
    private final Random random = new Random();

    public String generateOtp() {
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    public ResponseEntity<ApiResponseDto<String>> createOtpForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + email));

        String otp = generateOtp();
        user.setOtp(passwordEncoder.encode(otp)); // Save OTP as a hashed value
        user.setOtpCreationTime(LocalDateTime.now());
        user.setOtpExpirationTime(LocalDateTime.now().plusMinutes(otpExpirationTimeInMinutes));
        userRepository.save(user);

        // Send OTP to user's email
        emailService.sendOtpEmail(user.getEmail(), otp); // Implemented EmailService
        ApiResponseDto<String> response = new ApiResponseDto<>(true, "OTP has been sent to your email.", null, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<ApiResponseDto<String>> verifyOtp(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + email));

        if (user.getOtpExpirationTime().isBefore(LocalDateTime.now())) {
            ApiResponseDto<String> response = new ApiResponseDto<>(false, null, "OTP expired", HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        if (!passwordEncoder.matches(otp, user.getOtp())) {
            ApiResponseDto<String> response = new ApiResponseDto<>(false, null, "Invalid OTP", HttpStatus.UNAUTHORIZED.value());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // OTP is valid
        ApiResponseDto<String> response = new ApiResponseDto<>(true, "OTP is valid.", null, null);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<ApiResponseDto<String>> resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MESSAGE + email));

        user.setPassword(passwordEncoder.encode(newPassword)); // Encode new password
        userRepository.save(user);
        clearOtp(user); // Optionally clear OTP after successful password reset

        ApiResponseDto<String> response = new ApiResponseDto<>(true, "Password has been reset successfully.", null, null);
        return ResponseEntity.ok(response);
    }

    public void clearOtp(User user) {
        user.setOtp(null);
        user.setOtpCreationTime(null);
        user.setOtpExpirationTime(null);
        userRepository.save(user);
    }
}
