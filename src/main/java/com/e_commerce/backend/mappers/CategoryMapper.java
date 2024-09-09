package com.e_commerce.backend.mappers;

import com.e_commerce.backend.dtos.requests.CreateCategoryDto;
import com.e_commerce.backend.models.Category;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    public Category toCategory(CreateCategoryDto createCategoryDto) {
        Category newCategory = new Category();

        newCategory.setName(createCategoryDto.category_name());

        return newCategory;
    }

    public List<Category> toCategories(List<CreateCategoryDto> createCategoryDtos) {
        List<Category> categories = new ArrayList<>(createCategoryDtos.size());

        for(CreateCategoryDto categoryDto: createCategoryDtos) {
            categories.add(this.toCategory(categoryDto));
        }

        return categories;
    }
}
