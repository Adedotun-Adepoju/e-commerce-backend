package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.AddProductToCartDto;
import com.e_commerce.backend.dtos.requests.CreateCartDto;
import com.e_commerce.backend.dtos.requests.UpdateCartItemQuantity;
import com.e_commerce.backend.dtos.responses.CartDetailsResponseDto;
import com.e_commerce.backend.dtos.responses.CartItemResponseDto;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.models.CartItem;

import java.util.List;

public interface CartService {
    Cart createCart(CreateCartDto createCartDto);
    CartDetailsResponseDto fetchCartDetailsById(String cartId);
    CartItem addProductToCart(String cartId, AddProductToCartDto addProductToCartDto);
    List<CartItemResponseDto> fetchProductsInCart(String cartId);
    void removeProductFromCart(String cartId, String cartItemId);
    CartItem updateProductQuantity(String cartItemId, UpdateCartItemQuantity updateCartItemQuantity);
}
