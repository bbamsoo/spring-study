package com.example.querydsl.repository;

import com.example.querydsl.model.Category;
import com.example.querydsl.model.Product;
import com.example.querydsl.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findByProductAndCategory(Product product, Category category);
}
