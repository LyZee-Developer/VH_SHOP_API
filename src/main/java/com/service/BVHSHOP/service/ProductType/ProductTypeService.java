package com.service.BVHSHOP.service.ProductType;

import com.service.BVHSHOP.model.ProductType;
import com.service.BVHSHOP.request.ProductType.ProductTypeFilter;
import com.service.BVHSHOP.request.ProductType.ProductTypeReq;
import com.service.BVHSHOP.service.BaseInternalService;
import org.springframework.data.domain.Page;

public interface ProductTypeService extends BaseInternalService<ProductType, Long> {
    Page<ProductType> index(ProductTypeFilter filter);
    String create(ProductTypeReq req);
    String update(Long id, ProductTypeReq req);
    boolean checkCode(String code);
    String delete(Long id);
    ProductType findOneThrow(Long id);
}
