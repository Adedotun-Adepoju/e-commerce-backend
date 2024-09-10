package com.e_commerce.backend.mappers;

import com.e_commerce.backend.dtos.responses.CartDetailsResponseDto;
import com.e_commerce.backend.dtos.responses.CartItemResponseDto;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.models.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public CartItemResponseDto toCartItemResponseDto(CartItem cartItem) {
        return new CartItemResponseDto(cartItem.getId(), cartItem.getProduct().getTitle(), cartItem.getProduct().getImageUrl(), cartItem.getProduct().getPrice(), cartItem.getQuantity());
    }

    public CartDetailsResponseDto toCartDetailsResponseDto(Cart cart) {
        return new CartDetailsResponseDto(cart.getId(), cart.getItemsNumber());
    }
}
