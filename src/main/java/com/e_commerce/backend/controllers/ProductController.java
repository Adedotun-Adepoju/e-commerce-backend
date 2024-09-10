package com.e_commerce.backend.controllers;

import com.e_commerce.backend.dtos.requests.CreateProductDto;
import com.e_commerce.backend.dtos.responses.ApiResponseDto;
import com.e_commerce.backend.dtos.responses.ProductResponseDto;
import com.e_commerce.backend.models.Product;
import com.e_commerce.backend.services.ProductService;
import com.e_commerce.backend.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("")
    public ApiResponseDto<Product> createProduct(@RequestBody CreateProductDto createProductDto) {
        Product product = this.productService.createProduct(createProductDto);

        return ResponseUtil.success(product, "Product has been created");
    }

    @PostMapping("/create-multiple")
    public ApiResponseDto<List<Product>> createMultipleProducts(@RequestBody List<CreateProductDto> createProductDtos) {
        List<Product> products = this.productService.createMultipleProducts(createProductDtos);

        return ResponseUtil.success(products, "Products have been created");
    }


    @GetMapping("")
    public ApiResponseDto<List<ProductResponseDto>> fetchAllProducts(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "sort_direction", defaultValue="") String sortDirection
    ) {
        List<ProductResponseDto> products = this.productService.fetchAllProducts(page, limit, sortDirection.toLowerCase());

        return ResponseUtil.success(products, "Products have been fetched");
    }

    @GetMapping("/load-products")
    public ApiResponseDto<List<Product>> loadProductsFromFakeStore() {
        List<Product> products = this.productService.loadProducts();

        return ResponseUtil.success(products, "Products have been fetched");
    }

    @GetMapping("/{id}")
    public ApiResponseDto<ProductResponseDto> fetchProductById(@PathVariable("id") String id) {
        ProductResponseDto product = this.productService.fetchProductById(id);

        return ResponseUtil.success(product, "Product has been fetched");
    }
}
