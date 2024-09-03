package com.e_commerce.backend.modules.category;

import com.e_commerce.backend.libraries.dtos.ApiResponseDto;
import com.e_commerce.backend.libraries.dtos.product.CreateCategoryDto;
import com.e_commerce.backend.libraries.models.Category;
import com.e_commerce.backend.libraries.utils.ResponseUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Data
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ApiResponseDto<?> createProductCategory(@RequestBody CreateCategoryDto createCategoryDto){
        Category category = this.categoryService.createCategory(createCategoryDto);

        return ResponseUtil.success(category, "Category created successfully");
    }

    @GetMapping("")
    public ApiResponseDto<?> fetchCategories() {
        List<Category> categories = this.categoryService.fetchCategories();

        return ResponseUtil.success(categories, "Categories fetched successfully");
    }
}
