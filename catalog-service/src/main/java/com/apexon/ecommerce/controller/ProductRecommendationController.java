package com.apexon.ecommerce.controller;

import com.apexon.ecommerce.dto.ProductSearchResponseDTO;
import com.apexon.ecommerce.service.ProductRecommendationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRecommendationController {

    @Autowired
    private ProductRecommendationInterface recommendationService;

    @GetMapping("/{id}/related")
    public ResponseEntity<List<ProductSearchResponseDTO>> getRelatedProducts(@PathVariable Long id) {
        return ResponseEntity.ok(recommendationService.getRelatedProducts(id));
    }
}
