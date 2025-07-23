package com.apexon.ecommerce.controller;

import com.apexon.ecommerce.dto.ProductSearchRequestDTO;
import com.apexon.ecommerce.dto.ProductSearchResponseDTO;
import com.apexon.ecommerce.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductSearchController {

    @Autowired
    private ProductSearchService productSearchService;

    @PostMapping("/search")
    public ResponseEntity<List<ProductSearchResponseDTO>> searchProducts(@RequestBody ProductSearchRequestDTO dto) {
        return ResponseEntity.ok(productSearchService.searchProducts(dto));
    }
}

