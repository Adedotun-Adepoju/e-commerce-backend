package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateCategoryDto;
import com.e_commerce.backend.mappers.CategoryMapper;
import com.e_commerce.backend.models.Category;
import com.e_commerce.backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CreateCategoryDto createCategoryDto) {
        Category newCategory = this.categoryMapper.toCategory(createCategoryDto);

        return this.categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> createMultipleCategories(List<CreateCategoryDto> createCategoryDtos) {
        List<Category> categories = this.categoryMapper.toCategories(createCategoryDtos);

        return this.categoryRepository.saveAll(categories);
    }

    @Override
    public List<Category> fetchCategories() {
        return this.categoryRepository.findAll();
    }
}
