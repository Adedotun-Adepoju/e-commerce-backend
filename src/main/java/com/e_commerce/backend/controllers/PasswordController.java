package com.e_commerce.backend.controllers;

import com.e_commerce.backend.dtos.requests.CreateOtpDto;
import com.e_commerce.backend.dtos.requests.ResetPasswordDto;
import com.e_commerce.backend.dtos.requests.VerifyOtpDto;
import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.services.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/password")
@RequiredArgsConstructor
public class PasswordController {

    private final PasswordService passwordService;

    @PostMapping("/generate-otp")
    public ResponseEntity<ApiResponseDto<String>> generateOtp(@RequestBody CreateOtpDto createOtpDto) {
        return passwordService.createOtpForUser(createOtpDto.getEmail());
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponseDto<String>> verifyOtp(@RequestBody VerifyOtpDto verifyOtpDto) {
        return passwordService.verifyOtp(verifyOtpDto.getEmail(), verifyOtpDto.getOtp());
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponseDto<String>> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return passwordService.resetPassword(resetPasswordDto.getEmail(), resetPasswordDto.getNewPassword());
    }
}
