package com.e_commerce.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "products")
@RequiredArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // category id foreign key
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    private String title;

    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String image_url;

    private double average_rating;

    private int rating_counts;

    public Product(Category category, String title, double price, String description, String image_url, double average_rating, int rating_counts) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.image_url = image_url;
        this.average_rating = average_rating;
        this.rating_counts = rating_counts;
    }
}
