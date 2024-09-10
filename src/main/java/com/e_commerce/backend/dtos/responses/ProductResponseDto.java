package com.e_commerce.backend.dtos.responses;

public record ProductResponseDto(
        String id,
        String title,
        double price,
        String description,
        String image_url,
        String image_url_2,
        String image_url_3,
        String category_id,
        double average_rating,
        int rating_count
) {
}
