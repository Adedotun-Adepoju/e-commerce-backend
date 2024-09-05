package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateProductDto;
import com.e_commerce.backend.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> loadProducts();
    Product createProduct(CreateProductDto createProductDto);
    List<Product> createMultipleProducts(List<CreateProductDto> createProductDtos);
    List<Product> fetchAllProducts();
}
