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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

            Product newProduct = new Product();
            newProduct.setCategory(productCategory);
            newProduct.setTitle(product.getTitle());
            newProduct.setPrice(product.getPrice());
            newProduct.setDescription(product.getDescription());
            newProduct.setImageUrl(product.getImage());
            newProduct.setAverageRating(rating.getRate());
            newProduct.setRatingCounts(rating.getCount());
            newProduct.setExternalReference(product.getId());

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
    public List<ProductResponseDto> fetchAllProducts(int page, int limit, String sortDirection) {
        Sort.Direction direction = sortDirection.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Pageable pageable;

        if (!sortDirection.isEmpty()) {
            Sort sort = Sort.by(direction, "averageRating");
            pageable = PageRequest.of(page, limit, sort);
        } else {
            pageable = PageRequest.of(page, limit);
        }

//        Sort sort = Sort.by(direction, "averageRating");
//
//        Pageable pageable = PageRequest.of(page, limit, sort);

        Page<Product> pageProducts = this.productRepository.findAll(pageable);

        return pageProducts.getContent()
                .stream()
                .map(this.productMapper::toProductResponseDto)
                .toList();
    }
}
