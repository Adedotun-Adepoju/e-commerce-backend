package com.e_commerce.backend.mappers;

import com.e_commerce.backend.dtos.requests.CreateProductDto;
import com.e_commerce.backend.dtos.responses.ProductResponseDto;
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
        newProduct.setImageUrl(createProductDto.image_url());
        newProduct.setDescription(createProductDto.description());

        return newProduct;
    }

    public List<Product> toProducts(List<CreateProductDto> createProductDtos) {
        ArrayList<Product> products = new ArrayList<>(createProductDtos.size());

        for (CreateProductDto createProductDto : createProductDtos) {
            products.add(this.toProduct(createProductDto));
        }

        return products;
    }

    public ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getTitle(),
                product.getPrice(),
                product.getDescription(),
                product.getImageUrl(),
                product.getCategory().getId(),
                product.getAverageRating(),
                product.getRatingCounts()
        );
    }
}
