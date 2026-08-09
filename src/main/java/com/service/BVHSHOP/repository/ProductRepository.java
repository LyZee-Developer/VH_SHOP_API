package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.Product;
/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:22 PM
 */
public interface ProductRepository extends BaseInternalActivateRepository<Product, Long>{
    boolean existsByCodeAndIsActivateTrue(String code);
}
