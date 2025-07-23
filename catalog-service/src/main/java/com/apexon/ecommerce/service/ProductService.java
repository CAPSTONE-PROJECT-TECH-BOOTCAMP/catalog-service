package com.apexon.ecommerce.service;

import com.apexon.ecommerce.dto.ProductRequestDTO;
import com.apexon.ecommerce.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO addProduct(ProductRequestDTO dto);

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto);

    void deleteProduct(Long id);
}
