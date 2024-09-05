package com.e_commerce.backend.dtos.requests;

public record UpdateCartProductDto(
        String cart_item_id,
        int quantity
) {
}
