package com.e_commerce.backend.dtos.requests;

public record CreateProductDto(
        String name,
        String description,
        String image_url,
        String category,
        double price,
        double average_rating,
        int rating_counts
) {
}
