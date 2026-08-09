package com.service.BVHSHOP.service.ProductTypeItem;

import com.service.BVHSHOP.model.ProductTypeItem;
import com.service.BVHSHOP.request.ProductTypeItem.ProductTypeItemReq;
import com.service.BVHSHOP.service.BaseInternalService;

import java.util.List;

public interface ProductTypeItemService extends BaseInternalService<ProductTypeItem, Long> {
    List<ProductTypeItem> listItem(ProductTypeItemReq req);
    String create(ProductTypeItemReq req);
    String update(Long id, ProductTypeItemReq req);
    String delete(Long id);
    ProductTypeItem findOneThrow(Long id);
}
