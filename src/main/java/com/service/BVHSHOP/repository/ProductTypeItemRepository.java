package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.ProductTypeItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTypeItemRepository extends BaseInternalActivateRepository<ProductTypeItem, Long>, JpaSpecificationExecutor<ProductTypeItem> {

}
