package com.apexon.ecommerce.service;

import com.apexon.ecommerce.dto.ProductRequestDTO;
import com.apexon.ecommerce.dto.ProductResponseDTO;
import com.apexon.ecommerce.exception.ResourceNotFoundException;
import com.apexon.ecommerce.model.Category;
import com.apexon.ecommerce.model.Product;
import com.apexon.ecommerce.repository.CategoryRepository;
import com.apexon.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ProductResponseDTO addProduct(ProductRequestDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setInventoryCount(dto.getInventoryCount());
        product.setImageURL(dto.getImageUrl());
        product.setCategory(category);
        product.setBrand(dto.getBrand());

        Product saved = productRepository.save(product);
        return mapToResponseDTO(saved);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return mapToResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(this::mapToResponseDTO).toList();
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setInventoryCount(dto.getInventoryCount());
        product.setImageURL(dto.getImageUrl());
        product.setCategory(category);
        product.setBrand(dto.getBrand());

        Product updated = productRepository.save(product);
        return mapToResponseDTO(updated);
    }



    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }

    private ProductResponseDTO mapToResponseDTO(Product product) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setInventoryCount(product.getInventoryCount());
        dto.setImageUrl(product.getImageURL());
        dto.setCategoryName(product.getCategory().getName());
        dto.setBrandName(product.getBrand());
//        dto.setCustomerRating(product.getCustomerRating());
        return dto;
    }
}

