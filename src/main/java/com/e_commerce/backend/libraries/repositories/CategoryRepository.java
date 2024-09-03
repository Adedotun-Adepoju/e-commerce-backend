package com.e_commerce.backend.libraries.repositories;

import com.e_commerce.backend.libraries.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
