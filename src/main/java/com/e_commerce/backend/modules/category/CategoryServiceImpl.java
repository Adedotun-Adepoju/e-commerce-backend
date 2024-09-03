package com.e_commerce.backend.modules.category;

import com.e_commerce.backend.libraries.dtos.product.CreateCategoryDto;
import com.e_commerce.backend.libraries.mappers.Product.CategoryMapper;
import com.e_commerce.backend.libraries.mappers.Product.ProductMapper;
import com.e_commerce.backend.libraries.models.Category;
import com.e_commerce.backend.libraries.repositories.CategoryRepository;
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
    public List<Category> fetchCategories() {
        return this.categoryRepository.findAll();
    }
}
