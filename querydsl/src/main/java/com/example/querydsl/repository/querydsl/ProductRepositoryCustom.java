package com.example.querydsl.repository.querydsl;

import com.example.querydsl.model.ProductSimpleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryCustom {
    Page<ProductSimpleResponseDto> searchAll(Long categoryId, String Keyword, Pageable pageable);
}
