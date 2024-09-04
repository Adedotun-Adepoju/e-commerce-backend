package com.e_commerce.backend.services;

import com.e_commerce.backend.dtos.requests.CreateProductDto;
import com.e_commerce.backend.dtos.responses.fakeStore.FakeStoreRating;
import com.e_commerce.backend.dtos.responses.fakeStore.FakeStoreResp;
import com.e_commerce.backend.mappers.ProductMapper;
import com.e_commerce.backend.models.Category;
import com.e_commerce.backend.models.Product;
import com.e_commerce.backend.repositories.CategoryRepository;
import com.e_commerce.backend.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Value("${fake_store_base_url}")
    private String baseUrl;

    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @Override
    @Transactional
    public List<Product> loadProducts() {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List> response = restTemplate.exchange(this.baseUrl, HttpMethod.GET, entity, List.class);

        List<FakeStoreResp> responseBody = response.getBody();
        System.out.println(responseBody);

        assert responseBody != null;

        List<Product> products = new ArrayList<>(responseBody.size());


        for (int i = 0; i < responseBody.size(); i++) {
            FakeStoreResp product = mapper.convertValue(responseBody.get(i), FakeStoreResp.class);
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
                    rating.getCount()
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
    public List<Product> createMultipleProducts(List<CreateProductDto> createProductDtos) {
        List<Product> products = this.productMapper.toProducts(createProductDtos);

        return this.productRepository.saveAll(products);
    }
}
