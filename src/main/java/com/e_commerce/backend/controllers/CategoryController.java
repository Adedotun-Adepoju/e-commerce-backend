package com.e_commerce.backend.controllers;

import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.dtos.requests.CreateCategoryDto;
import com.e_commerce.backend.models.Category;
import com.e_commerce.backend.services.CategoryService;
import com.e_commerce.backend.utils.ResponseUtil;
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
    public ApiResponseDto<Category> createProductCategory(@RequestBody CreateCategoryDto createCategoryDto){
        Category category = this.categoryService.createCategory(createCategoryDto);

        return ResponseUtil.success(category, "Category created successfully");
    }

    @PostMapping("create-multiple")
    public ApiResponseDto<List<Category>> createMultipleCategories(@RequestBody List<CreateCategoryDto> createCategoryDtos) {
        List<Category> categories = this.categoryService.createMultipleCategories(createCategoryDtos);

        return ResponseUtil.success(categories, "Categories have been created");
    }

    @GetMapping("")
    public ApiResponseDto<List<Category>> fetchCategories() {
        List<Category> categories = this.categoryService.fetchCategories();

        return ResponseUtil.success(categories, "Categories fetched successfully");
    }
}
