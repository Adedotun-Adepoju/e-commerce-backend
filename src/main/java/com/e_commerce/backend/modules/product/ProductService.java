package com.e_commerce.backend.modules.product;

import com.e_commerce.backend.libraries.dtos.product.CreateCategoryDto;
import com.e_commerce.backend.libraries.models.Category;

public interface ProductService {
    Category createCategory(CreateCategoryDto createCategoryDto);
}
