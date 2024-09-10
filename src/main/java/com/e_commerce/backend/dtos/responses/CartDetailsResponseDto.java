package com.e_commerce.backend.dtos.responses;

public record CartDetailsResponseDto(
        String cart_id,
        int items_number
) {
}
