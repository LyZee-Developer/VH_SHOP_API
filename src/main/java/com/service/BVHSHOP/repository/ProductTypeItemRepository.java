package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.ProductTypeItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:22 PM
 */
public interface ProductTypeItemRepository extends BaseInternalActivateRepository<ProductTypeItem, Long>, JpaSpecificationExecutor<ProductTypeItem> {

}
