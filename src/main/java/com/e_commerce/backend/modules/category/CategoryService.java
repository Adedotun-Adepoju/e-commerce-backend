package com.e_commerce.backend.modules.category;

import com.e_commerce.backend.libraries.dtos.product.CreateCategoryDto;
import com.e_commerce.backend.libraries.models.Category;

import javax.smartcardio.Card;
import java.util.List;

public interface CategoryService {
    Category createCategory(CreateCategoryDto createCategoryDto);
    List<Category> fetchCategories();
}
