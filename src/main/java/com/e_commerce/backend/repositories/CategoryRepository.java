package com.e_commerce.backend.repositories;

import com.e_commerce.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findCategoryByName(String name);
}
