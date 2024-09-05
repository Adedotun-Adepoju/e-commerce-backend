package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.AddProductToCartDto;
import com.e_commerce.backend.dtos.requests.CreateCartDto;
import com.e_commerce.backend.dtos.requests.UpdateCartProductDto;
import com.e_commerce.backend.dtos.responses.CartItemResponseDto;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.models.CartItem;

import java.util.List;

public interface CartService {
    Cart createCart(CreateCartDto createCartDto);
    Cart fetchCartDetailsById(String cartId);
    CartItem addProductToCart(String cartId, AddProductToCartDto addProductToCartDto);
    List<CartItemResponseDto> fetchProductsInCart(String cartId);
    List<CartItemResponseDto> updateProductsInCart(String cartId, List<UpdateCartProductDto> updateCartProductDto);
    void removeProductFromCart(String cartItemId);
//    void updateCartItemNo(String cartId);
}
