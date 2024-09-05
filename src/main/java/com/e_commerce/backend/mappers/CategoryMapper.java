package com.e_commerce.backend.mappers;

import com.e_commerce.backend.dtos.requests.CreateCategoryDto;
import com.e_commerce.backend.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toCategory(CreateCategoryDto createCategoryDto) {
        Category newCategory = new Category();

        newCategory.setName(createCategoryDto.category_name());

        return newCategory;
    }
}
