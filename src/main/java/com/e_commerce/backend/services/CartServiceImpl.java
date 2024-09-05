package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.AddProductToCartDto;
import com.e_commerce.backend.dtos.requests.CreateCartDto;
import com.e_commerce.backend.dtos.requests.UpdateCartProductDto;
import com.e_commerce.backend.dtos.responses.CartItemResponseDto;
import com.e_commerce.backend.mappers.CartMapper;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.models.CartItem;
import com.e_commerce.backend.models.Product;
import com.e_commerce.backend.repositories.CartItemRepository;
import com.e_commerce.backend.repositories.CartRepository;
import com.e_commerce.backend.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartMapper;

    @Override
    public Cart createCart(CreateCartDto createCartDto) {
        Cart cart = new Cart();

        cart.setUserId(createCartDto.user_id());

        return this.cartRepository.save(cart);
    }

    @Override
    public Cart fetchCartDetailsById(String cartId) {
        return this.cartRepository.findById(cartId).orElseThrow();
    }

    @Override
    public CartItem addProductToCart(String cartId, AddProductToCartDto addProductToCartDto) {
        Cart userCart = this.cartRepository.findById(cartId)
                .orElseThrow();

        Product product = this.productRepository.findById(addProductToCartDto.product_id())
                .orElseThrow();

        CartItem newCartItem = new CartItem();

        newCartItem.setCart(userCart);
        newCartItem.setProduct(product);
        newCartItem.setQuantity(addProductToCartDto.quantity());

        return this.cartItemRepository.save(newCartItem);
    }

    @Override
    public List<CartItemResponseDto> fetchProductsInCart(String cartId) {
        List<CartItem> cartItems = this.cartItemRepository.findAllByCartId(cartId);

        return cartItems.stream()
                .map(this.cartMapper::toCartItemResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public List<CartItemResponseDto> updateProductsInCart(String cartId, List<UpdateCartProductDto> updateCartProductDtos) {
        List<CartItem> updatedCartItems = new ArrayList<>(updateCartProductDtos.size());

        for (UpdateCartProductDto updateCartProductDto: updateCartProductDtos) {
            CartItem cartItem = this.cartItemRepository.findById(updateCartProductDto.cart_item_id())
                    .orElseThrow();

            if (!cartItem.getCart().getId().equals(cartId)) {
                throw new IllegalArgumentException("Cart does not exist for this product");
            }

            cartItem.setQuantity(updateCartProductDto.quantity());

            updatedCartItems.add(this.cartItemRepository.save(cartItem));
        }

        return updatedCartItems.stream()
                .map(this.cartMapper::toCartItemResponseDto)
                .toList();
    }

    @Override
    public void removeProductFromCart(String cartItemId) {
        this.cartItemRepository.deleteById(cartItemId);
    }
}
