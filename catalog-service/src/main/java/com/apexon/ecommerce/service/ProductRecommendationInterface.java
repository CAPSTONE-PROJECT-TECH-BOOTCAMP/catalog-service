package com.apexon.ecommerce.service;

import com.apexon.ecommerce.dto.ProductSearchResponseDTO;

import java.util.List;

public interface ProductRecommendationInterface {
    List<ProductSearchResponseDTO> getRelatedProducts(Long productId);
}
