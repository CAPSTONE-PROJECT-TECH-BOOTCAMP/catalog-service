package com.apexon.ecommerce.service;

import com.apexon.ecommerce.dto.ProductSearchResponseDTO;
import com.apexon.ecommerce.exception.ResourceNotFoundException;
import com.apexon.ecommerce.model.Product;
import com.apexon.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.awt.print.Pageable;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductRecommendationServiceImpl implements ProductRecommendationInterface {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductSearchResponseDTO> getRelatedProducts(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        List<Product> related = productRepository.findRelatedProducts(
                product.getCategory().getId(), productId, PageRequest.of(0, 5));

        return related.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private ProductSearchResponseDTO mapToDTO(Product product) {
        ProductSearchResponseDTO dto = new ProductSearchResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setInventoryCount(product.getInventoryCount());
        dto.setImageURL(product.getImageURL());
        dto.setBrand(product.getBrand());
        dto.setCategoryName(product.getCategory().getName());
        return dto;
    }
}
