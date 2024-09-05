package com.e_commerce.backend.repositories;

import com.e_commerce.backend.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, String> {
}
