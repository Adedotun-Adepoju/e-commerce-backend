package com.e_commerce.backend.dtos.responses;

public record CartItemResponseDto(
        String id,
        String title,
        String image_url,
        double price,
        int quantity
) {
}
