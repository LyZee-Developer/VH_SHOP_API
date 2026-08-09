package com.service.BVHSHOP.service.Product;

import com.service.BVHSHOP.model.Product;
import com.service.BVHSHOP.request.Product.ProductFilter;
import com.service.BVHSHOP.request.Product.ProductReq;
import com.service.BVHSHOP.service.BaseInternalService;
import org.springframework.data.domain.Page;

public interface ProductService extends BaseInternalService<Product, Long> {
    Page<Product> index(ProductFilter filter);
    String create(ProductReq req);
    String update(Long id, ProductReq req);
    boolean checkCode(String code);
    String delete(Long id);
}
