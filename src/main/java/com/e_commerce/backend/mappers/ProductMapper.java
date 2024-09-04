package com.e_commerce.backend.mappers;

import com.e_commerce.backend.dtos.requests.CreateProductDto;
import com.e_commerce.backend.models.Category;
import com.e_commerce.backend.models.Product;
import com.e_commerce.backend.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductMapper {
    private final CategoryRepository categoryRepository;

    public Product toProduct(CreateProductDto createProductDto) {
        Product newProduct = new Product();

        Category productCategory = this.categoryRepository.findCategoryByName(createProductDto.category().toLowerCase())
                .orElseThrow();
        // throw error if category is null

        newProduct.setTitle(createProductDto.name());
        newProduct.setCategory(productCategory);
        newProduct.setPrice(createProductDto.price());
        newProduct.setImage_url(createProductDto.image_url());
        newProduct.setDescription(createProductDto.description());

        return newProduct;
    }

    public List<Product> toProducts(List<CreateProductDto> createProductDtos) {
        ArrayList<Product> products = new ArrayList<>(createProductDtos.size());

        System.out.println(createProductDtos.size());

        for (int i = 0; i < createProductDtos.size(); i++) {
            products.add(this.toProduct(createProductDtos.get(i)));
        }

        return products;
    }
}
