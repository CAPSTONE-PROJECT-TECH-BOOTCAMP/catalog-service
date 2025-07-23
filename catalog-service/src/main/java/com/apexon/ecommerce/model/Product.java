package com.apexon.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2) // Precision for currency
    private BigDecimal price;

    @Column(nullable = false)
    private Integer inventoryCount;

    @Column(nullable = false)
    private boolean isActive = true; // Products are active by default

    @ManyToOne(fetch = FetchType.LAZY) // Many products can belong to one category
    @JoinColumn(name = "category_id", nullable = false) // Foreign key to categories table
    private Category category;

    private String imageURL;

    private String brand;

    @Column(precision = 3, scale = 2) // e.g., 4.50
    private static Double averageRating;

    //Vendor Details - List of vendor details

//    @Column(nullable = false, unique = true) // SKU should be unique
//    private String sku;
//
//    @Column(precision = 10, scale = 2) // Precision for weight
//    private BigDecimal weight;
//
//    private String dimensions; // Store as a string, e.g., "10x5x2 cm"

    // We'll add the ProductImage and AttributeValue relationships here later
//     @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//     private String images;

    // @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    // private List<AttributeValue> attributeValues;


//    // Audit fields
//    @Column(nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @Column(nullable = false)
//    private LocalDateTime updatedAt;

//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
