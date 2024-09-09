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

        newProduct.setTitle(createProductDto.title());
        newProduct.setCategory(productCategory);
        newProduct.setPrice(createProductDto.price());
        newProduct.setThumbnail(createProductDto.thumbnail());
        newProduct.setImageUrl(createProductDto.image_url());
        newProduct.setImageUrl2(createProductDto.image_url_2());
        newProduct.setImageUrl3(createProductDto.image_url_3());
        newProduct.setDescription(createProductDto.description());
        newProduct.setExternalReference(createProductDto.external_reference());
        newProduct.setAverageRating(createProductDto.average_rating());

        return newProduct;
    }

    public List<Product> toProducts(List<CreateProductDto> createProductDtos) {
        ArrayList<Product> products = new ArrayList<>(createProductDtos.size());

        for (CreateProductDto createProductDto : createProductDtos) {
            products.add(this.toProduct(createProductDto));
        }

        return products;
    }
}
