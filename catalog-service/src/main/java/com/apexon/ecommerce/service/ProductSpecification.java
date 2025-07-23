package com.apexon.ecommerce.service;

import com.apexon.ecommerce.dto.ProductSearchRequestDTO;
import com.apexon.ecommerce.model.Product;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<Product> build(ProductSearchRequestDTO dto) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.getKeyword() != null && !dto.getKeyword().isEmpty()) {
                Predicate namePredicate = cb.like(cb.lower(root.get("name")), "%" + dto.getKeyword().toLowerCase() + "%");
                Predicate descPredicate = cb.like(cb.lower(root.get("description")), "%" + dto.getKeyword().toLowerCase() + "%");
                predicates.add(cb.or(namePredicate, descPredicate));
            }

            if (dto.getCategoryId() != null) {
                predicates.add(cb.equal(root.get("category").get("id"), dto.getCategoryId()));
            }

            if (dto.getMinPrice() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("price"), dto.getMinPrice()));
            }

            if (dto.getMaxPrice() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("price"), dto.getMaxPrice()));
            }

            if (dto.getBrand() != null && !dto.getBrand().isEmpty()) {
                predicates.add(cb.equal(cb.lower(root.get("brand")), dto.getBrand().toLowerCase()));
            }

            if (dto.getInStock() != null && dto.getInStock()) {
                predicates.add(cb.greaterThan(root.get("inventoryCount"), 0));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
