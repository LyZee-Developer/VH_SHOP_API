package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.Category;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends BaseInternalActivateRepository<Category,Long>, JpaSpecificationExecutor<Category> {
    boolean existsByCodeAndIsActivate(String code,Boolean isActivate);
}
