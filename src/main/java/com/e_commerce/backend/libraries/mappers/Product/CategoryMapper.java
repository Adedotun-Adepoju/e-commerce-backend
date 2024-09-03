package com.e_commerce.backend.libraries.mappers.Product;

import com.e_commerce.backend.libraries.dtos.product.CreateCategoryDto;
import com.e_commerce.backend.libraries.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toCategory(CreateCategoryDto createCategoryDto) {
        Category newCategory = new Category();

        newCategory.setName(createCategoryDto.category_name());

        return newCategory;
    }
}
