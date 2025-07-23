package com.apexon.ecommerce.service;

import com.apexon.ecommerce.dto.ProductSearchRequestDTO;
import com.apexon.ecommerce.dto.ProductSearchResponseDTO;
import com.apexon.ecommerce.model.Product;
import com.apexon.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductSearchResponseDTO> searchProducts(ProductSearchRequestDTO dto) {
        Specification<Product> spec = ProductSpecification.build(dto);
        List<Product> products = productRepository.findAll(spec);

        return products.stream().map(this::mapToDTO).collect(Collectors.toList());
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
