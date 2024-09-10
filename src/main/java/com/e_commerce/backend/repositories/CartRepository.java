package com.e_commerce.backend.repositories;

import com.e_commerce.backend.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, String> {
}
