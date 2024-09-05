package com.e_commerce.backend.controllers;

import com.e_commerce.backend.dtos.requests.CreateCartDto;
import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.models.Cart;
import com.e_commerce.backend.services.CartService;
import com.e_commerce.backend.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping("")
    public ApiResponseDto<Cart> createCart(@RequestBody CreateCartDto createCartDto) {
        Cart cart = this.cartService.createCart(createCartDto);

        return ResponseUtil.success(cart, "Cart created successfully");
    }
}
