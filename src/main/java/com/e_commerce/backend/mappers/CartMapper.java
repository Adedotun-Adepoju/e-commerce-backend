package com.e_commerce.backend.mappers;

import com.e_commerce.backend.dtos.responses.CartItemResponseDto;
import com.e_commerce.backend.models.CartItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {
    public CartItemResponseDto toCartItemResponseDto(CartItem cartItem) {
        return new CartItemResponseDto(cartItem.getId(), cartItem.getProduct().getTitle(), cartItem.getProduct().getPrice(), cartItem.getQuantity());
    }
}
