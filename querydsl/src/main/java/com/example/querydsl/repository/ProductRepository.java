package com.example.querydsl.repository;

import com.example.querydsl.model.Product;
import com.example.querydsl.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
성