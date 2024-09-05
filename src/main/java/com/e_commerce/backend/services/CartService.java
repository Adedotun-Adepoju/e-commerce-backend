package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateCartDto;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.models.CartItem;

import java.util.List;

public interface CartService {
    Cart createCart(CreateCartDto createCartDto);
    Cart fetchCartDetailsById(String cartId);
    CartItem addProductToCart(String productId, String userId);
    List<CartItem> fetchProductsInCart(String cartId);
    List<CartItem> updateProductQuantity(String cartItemId, int amount);
    List<CartItem> removeProductFromCart(String cartItemId);
}
