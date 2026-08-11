package com.service.BVHSHOP.specification;

import com.service.BVHSHOP.model.*;
import com.service.BVHSHOP.request.Category.CategoryFilter;
import com.service.BVHSHOP.request.Product.ProductFilter;
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
 * @Date : 8/11/2026 10:21 PM
 */
@RequiredArgsConstructor
public class CategorySpecification implements BaseInternalSpecification<Category> {

    private final CategoryFilter filter;

    @Override
    public @Nullable Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String searchTrim = Optional.of(filter.getSearch()).map(String::toLowerCase).map(String::trim).orElse("");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(Product_.IS_ACTIVATE), Boolean.TRUE));
        if (StringUtils.hasLength(searchTrim)) {
            predicates.add(cb.or(
                    cb.like(cb.lower(root.get(Category_.CODE)), "%" + searchTrim + "%"),
                    cb.like(cb.lower(root.get(Category_.ENGLISH_NAME)), "%" + searchTrim + "%"),
                    cb.like(cb.lower(root.get(Category_.NAME)), "%" + searchTrim + "%")
            ));
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
