package com.service.BVHSHOP.service.Product;

import com.service.BVHSHOP.model.Product;
import com.service.BVHSHOP.model.ProductPrice;
import com.service.BVHSHOP.request.Product.ProductReq;
import com.service.BVHSHOP.service.BaseInternalService;

import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:39 PM
 */
public interface ProductPriceService extends BaseInternalService<ProductPrice, Long> {
    void saveAllPrice(List<ProductReq.Price> productPrice, Product product);
}
