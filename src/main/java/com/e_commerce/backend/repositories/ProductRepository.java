package com.e_commerce.backend.repositories;

import com.e_commerce.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findProductByExternalReference(int id);
}
