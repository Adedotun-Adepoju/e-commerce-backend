package com.e_commerce.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<CartItem> cartItems;

    private String title;

    private Double price;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String imageUrl;

    private double averageRating;

    private int ratingCounts;

    private int externalReference;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product(Category category, String title, double price, String description, String imageUrl, double averageRating, int ratingCounts, int externalReference) {
        this.category = category;
        this.title = title;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.averageRating = averageRating;
        this.ratingCounts = ratingCounts;
        this.externalReference = externalReference;
    }
}
