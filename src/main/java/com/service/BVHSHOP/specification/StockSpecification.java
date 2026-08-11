package com.service.BVHSHOP.specification;

import com.service.BVHSHOP.model.*;
import com.service.BVHSHOP.request.Stock.StockFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 8:59 PM
 */
@RequiredArgsConstructor
public class StockSpecification implements BaseInternalSpecification<Stock> {
    private final StockFilter filter;

    @Override
    public @Nullable Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String searchTrim = Optional.of(filter.getSearch()).map(String::toLowerCase).map(String::trim).orElse("");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Stock_.IS_ACTIVATE), Boolean.TRUE));
        if (StringUtils.hasLength(searchTrim)) {
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get(Stock_.productSale).get(ProductPrice_.PRODUCT).get(Product_.NAME)), "%" + searchTrim + "%"),
                    cb.like(cb.lower(root.get(Stock_.productSale).get(ProductPrice_.PRODUCT).get(Product_.ENGLISH_NAME)), "%" + searchTrim + "%"),
                    cb.like(cb.lower(root.get(Stock_.productSale).get(ProductPrice_.PRODUCT).get(Product_.CODE)), "%" + searchTrim + "%")
            ));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
