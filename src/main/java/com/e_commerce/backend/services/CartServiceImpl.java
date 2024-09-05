package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateCartDto;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.models.CartItem;
import com.e_commerce.backend.repositories.CartItemRepository;
import com.e_commerce.backend.repositories.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart createCart(CreateCartDto createCartDto) {
        Cart cart = new Cart();

        cart.setUserId(createCartDto.user_id());

        return this.cartRepository.save(cart);
    }

    @Override
    public Cart fetchCartDetailsById(String cartId) {
        return null;
    }

    @Override
    public CartItem addProductToCart(String productId, String userId) {
        return null;
    }

    @Override
    public List<CartItem> fetchProductsInCart(String cartId) {
        return List.of();
    }

    @Override
    public List<CartItem> updateProductQuantity(String cartItemId, int amount) {
        return List.of();
    }

    @Override
    public List<CartItem> removeProductFromCart(String cartItemId) {
        return List.of();
    }
}
