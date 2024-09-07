package com.e_commerce.backend.dtos.requests;

public record CreateProductDto(
        String title,
        String description,
        String thumbnail,
        String image_url,
        String image_url_2,
        String image_url_3,
        String category,
        double price,
        double average_rating,
        int rating_counts,
        int external_reference
) {
}
