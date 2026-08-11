package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.Stock;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 6:15 AM
 */
public interface StockRepository extends BaseInternalActivateRepository<Stock, Long>{
    boolean existsByProductSaleIdAndIsActivateTrue(Long id);
}
