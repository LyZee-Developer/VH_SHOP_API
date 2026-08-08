package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>, JpaSpecificationExecutor<Category> {
    boolean existsByCodeAndIsActivate(String code,Boolean isActivate);
}
