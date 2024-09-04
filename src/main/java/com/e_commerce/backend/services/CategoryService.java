package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateCategoryDto;
import com.e_commerce.backend.models.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(CreateCategoryDto createCategoryDto);
    List<Category> fetchCategories();
}
