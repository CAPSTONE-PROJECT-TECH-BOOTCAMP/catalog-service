package com.apexon.ecommerce.service;

import com.apexon.ecommerce.dto.ProductSearchRequestDTO;
import com.apexon.ecommerce.dto.ProductSearchResponseDTO;

import java.util.List;

public interface ProductSearchService {

    List<ProductSearchResponseDTO> searchProducts(ProductSearchRequestDTO dto);

}
