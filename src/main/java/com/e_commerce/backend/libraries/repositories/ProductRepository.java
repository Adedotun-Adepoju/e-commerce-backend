package com.e_commerce.backend.libraries.repositories;

import com.e_commerce.backend.libraries.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
