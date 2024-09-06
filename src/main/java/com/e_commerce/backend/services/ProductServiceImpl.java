package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateProductDto;
import com.e_commerce.backend.dtos.responses.ProductResponseDto;
import com.e_commerce.backend.dtos.responses.fakeStore.FakeStoreRating;
import com.e_commerce.backend.dtos.responses.fakeStore.FakeStoreResp;
import com.e_commerce.backend.mappers.ProductMapper;
import com.e_commerce.backend.models.Category;
import com.e_commerce.backend.models.Product;
import com.e_commerce.backend.repositories.CategoryRepository;
import com.e_commerce.backend.repositories.ProductRepository;
import com.e_commerce.backend.utils.ApiRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Value("${fake_store_base_url}")
    private String fakeStoreUrl;

    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @Override
    @Transactional
    public List<Product> loadProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List> response = ApiRequestUtil.makeGetRequest(fakeStoreUrl, headers, List.class);

        List<FakeStoreResp> responseBody = response.getBody();
        assert responseBody != null;

        log.info(responseBody.toString());

        List<Product> products = new ArrayList<>(responseBody.size());

        for (int i = 0; i < responseBody.size(); i++) {
            // check if the product already exists in the db
            FakeStoreResp product = mapper.convertValue(responseBody.get(i), FakeStoreResp.class);

            Optional<Product> existingProduct = this.productRepository.findProductByExternalReference(product.getId());

            // Skip product if it exists in the DB
            if (existingProduct.isPresent()) {
                continue;
            }

            Category productCategory = this.categoryRepository.findCategoryByName(product.getCategory())
                    .orElseThrow();

            // retrieve nested hashmap
            FakeStoreRating rating = product.getRating();

            Product newProduct = new Product(productCategory,
                    product.getTitle(),
                    product.getPrice(),
                    product.getDescription(),
                    product.getImage(),
                    rating.getRate(),
                    rating.getCount(),
                    product.getId()
            );

            products.add(this.productRepository.save(newProduct));
        }

        return products;
    }

    @Override
    public Product createProduct(CreateProductDto createProductDto) {
        Product newProduct = this.productMapper.toProduct(createProductDto);

        return this.productRepository.save(newProduct);
    }

    @Override
    public ProductResponseDto fetchProductById(String id) {
        Product product = this.productRepository.findById(id).orElseThrow();

        return this.productMapper.toProductResponseDto(product);
    }

    @Override
    public List<Product> createMultipleProducts(List<CreateProductDto> createProductDtos) {
        List<Product> products = this.productMapper.toProducts(createProductDtos);

        return this.productRepository.saveAll(products);
    }

    @Override
    public List<ProductResponseDto> fetchAllProducts() {
        List<Product> products =  this.productRepository.findAll();

        return products.stream()
                .map(this.productMapper::toProductResponseDto)
                .collect(Collectors.toList());
    }
}
