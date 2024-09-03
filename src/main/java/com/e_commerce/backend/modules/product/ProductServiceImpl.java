package com.e_commerce.backend.modules.product;

import com.e_commerce.backend.libraries.dtos.product.CreateCategoryDto;
import com.e_commerce.backend.libraries.mappers.Product.ProductMapper;
import com.e_commerce.backend.libraries.models.Category;
import com.e_commerce.backend.libraries.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductMapper productMapper;
    private CategoryRepository categoryRepository;
}
