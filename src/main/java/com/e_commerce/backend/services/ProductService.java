package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateProductDto;
import com.e_commerce.backend.dtos.responses.ProductResponseDto;
import com.e_commerce.backend.models.Product;
import org.hibernate.query.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {
    List<Product> loadProducts();
    Product createProduct(CreateProductDto createProductDto);
    ProductResponseDto fetchProductById(String id);
    List<Product> createMultipleProducts(List<CreateProductDto> createProductDtos);
    List<ProductResponseDto> fetchAllProducts(int page, int limit, String sortDirection);
}
