package com.e_commerce.backend.controllers;

import com.e_commerce.backend.dtos.requests.AddProductToCartDto;
import com.e_commerce.backend.dtos.requests.CreateCartDto;
import com.e_commerce.backend.dtos.requests.UpdateCartProductDto;
import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.dtos.responses.CartItemResponseDto;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.models.CartItem;
import com.e_commerce.backend.services.CartService;
import com.e_commerce.backend.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<Cart> createCart(@RequestBody CreateCartDto createCartDto) {
        Cart cart = this.cartService.createCart(createCartDto);

        return ResponseUtil.success(cart, "Cart created successfully");
    }

    @GetMapping("/{cart_id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<Cart> getCartById(@PathVariable("cart_id") String cartId) {
        Cart cart = this.cartService.fetchCartDetailsById(cartId);

        return ResponseUtil.success(cart,"Cart details have been fetched");
    }

    @PostMapping("/{cart_id}/product")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponseDto<CartItem> addProductToCart(
                @PathVariable("cart_id") String cartId,
                @RequestBody AddProductToCartDto addProductToCartDto
        ) {
        CartItem cartItem = this.cartService.addProductToCart(cartId, addProductToCartDto);

        return ResponseUtil.success(cartItem, "Product has been added to cart");
    }

    @GetMapping("/{cart_id}/products")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<CartItemResponseDto>> fetchProductsByCartId(
            @PathVariable("cart_id") String cartId
    ) {
        List<CartItemResponseDto> cartItems = this.cartService.fetchProductsInCart(cartId);

        return ResponseUtil.success(cartItems, "Carts products have been fetched");
    }

    @PutMapping("/{cart_id}/products")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponseDto<List<CartItemResponseDto>> updateCartItems(
            @PathVariable("cart_id") String cartId,
            @RequestBody List<UpdateCartProductDto> updateCartProductDtos
        ){

        List<CartItemResponseDto> cartItemResponseDtos = this.cartService.updateProductsInCart(cartId, updateCartProductDtos);

        return ResponseUtil.success(cartItemResponseDtos, "Cart products have been updated");
    }

    @DeleteMapping("/{cart_id}/cart-item/{cart_item_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ApiResponseDto<Object> removeProductFromCart(
            @PathVariable("cart_id") String cartId,
            @PathVariable("cart_item_id") String cardItemId
    ) {
        this.cartService.removeProductFromCart(cardItemId);

        return ResponseUtil.success(null, "Product has been removed from cart");
    }
}
