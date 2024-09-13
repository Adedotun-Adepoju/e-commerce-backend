package com.e_commerce.backend.dtos.responses;

public record LoginResponseDto(
        String email,
        String jwt,
        String id
) {
}
