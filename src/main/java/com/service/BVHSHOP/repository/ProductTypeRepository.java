package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.ProductType;

public interface ProductTypeRepository extends BaseInternalActivateRepository<ProductType, Long> {
    boolean existsByCodeAndIsActivateTrue(String code);
}
