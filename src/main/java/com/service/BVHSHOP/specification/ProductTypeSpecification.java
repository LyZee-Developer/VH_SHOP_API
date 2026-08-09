package com.service.BVHSHOP.specification;

import com.service.BVHSHOP.model.ProductType;
import com.service.BVHSHOP.model.ProductType_;
import com.service.BVHSHOP.request.ProductType.ProductTypeFilter;
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

@RequiredArgsConstructor
public class ProductTypeSpecification implements BaseInternalSpecification<ProductType> {
    private final ProductTypeFilter filter;

    @Override
    public @Nullable Predicate toPredicate(Root<ProductType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        String searchTrim = Optional.of(filter.getSearch().trim().toLowerCase()).orElse("");

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.equal(root.get(ProductType_.IS_ACTIVATE), Boolean.TRUE));
        if (StringUtils.hasLength(searchTrim)) {
            predicates.add(
                    cb.or(
                        cb.equal(cb.lower(root.get(ProductType_.NAME)), searchTrim),
                        cb.equal(cb.lower(root.get(ProductType_.ENGLISH_NAME)), searchTrim),
                        cb.equal(cb.lower(root.get(ProductType_.CODE)), searchTrim)
                    )
            );
        }
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
