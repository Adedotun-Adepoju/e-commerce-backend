package com.e_commerce.backend.dtos.responses;

public record CartItemResponseDto(
        String id,
        String title,
        double price,
        int quantity
) {
}
