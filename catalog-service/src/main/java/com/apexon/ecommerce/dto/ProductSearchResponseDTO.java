package com.apexon.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer inventoryCount;
    private String imageURL;
    private String brand;
    private String categoryName;
}

