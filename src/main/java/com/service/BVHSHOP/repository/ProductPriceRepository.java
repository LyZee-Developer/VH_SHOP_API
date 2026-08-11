package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.ProductPrice;

import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:22 PM
 */
public interface ProductPriceRepository extends BaseInternalActivateRepository<ProductPrice, Long>{
    List<ProductPrice> findByProductItemAndIsActivateTrue(Long productId);
}
