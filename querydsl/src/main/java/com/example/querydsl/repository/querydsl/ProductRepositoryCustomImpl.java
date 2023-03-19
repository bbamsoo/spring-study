package com.example.querydsl.repository.querydsl;

import com.example.querydsl.model.ProductSimpleResponseDto;
import com.example.querydsl.model.QProductSimpleResponseDto;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.querydsl.model.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProductSimpleResponseDto> searchAll(Long categoryId, String keyword, Pageable pageable) {
        // 제품당 별점
        SimpleExpression<Double> rating = Expressions.as(JPAExpressions
                .select(review.rating.avg())
                .from(review)
                .where(product.productId.eq(review.product.productId)), "rating");

        // 메인 쿼리
        JPAQuery<ProductSimpleResponseDto> query = queryFactory
                .select(new QProductSimpleResponseDto(
                        product.productId,
                        product.name,
                        product.price,
                        product.photo,
                        product.productStatus.stringValue(),
                        rating))
                .from(product)
                .where(
                        eqCategory(categoryId),
                        eqKeyword(keyword)
                );

        // 정렬 조건
        List<ProductSimpleResponseDto> result = query
                .orderBy(getOrderSpecifier(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return PageableExecutionUtils.getPage(result, pageable, query::fetchCount);
    }

    private BooleanExpression eqCategory(Long categoryId) {
        return categoryId == null
                ? null
                : product.productCategories.any().category.categoryId.eq(categoryId);
    }

    private BooleanExpression eqKeyword(String keyword) {
        return keyword == null
                ? null
                : product.name.contains(keyword)
                .or(product.description.contains(keyword))
                .or(product.brand.contains(keyword));
    }

    private OrderSpecifier getOrderSpecifier(Pageable pageable) {
        Sort.Order direction = pageable.getSort().get().collect(Collectors.toList()).get(0);

        Order order = direction.getDirection().isAscending()
                ? Order.ASC
                : Order.DESC;

        switch (direction.getProperty()) {
            case "name":
                return new OrderSpecifier(order, product.name);
            case "price":
                return new OrderSpecifier(order, product.price);
            case "brand":
                return new OrderSpecifier(order, product.brand);
            case "likeCount":
                return new OrderSpecifier(order, product.likes.size());
            case "soldCount":
                return new OrderSpecifier(order, product.soldCount);
            case "rating":
                return new OrderSpecifier<>(order, Expressions.stringPath("rating"));
            default:
                return new OrderSpecifier(order, product.productId);
        }
    }
}
