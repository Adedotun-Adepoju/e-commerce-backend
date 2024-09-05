package com.e_commerce.backend.dtos.requests;

public record AddProductToCartDto(
        String product_id,
        int quantity
) {
}
