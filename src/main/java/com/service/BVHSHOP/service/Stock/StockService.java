package com.service.BVHSHOP.service.Stock;

import com.service.BVHSHOP.model.Stock;
import com.service.BVHSHOP.request.Stock.StockFilter;
import com.service.BVHSHOP.request.Stock.StockReq;
import com.service.BVHSHOP.service.BaseInternalService;
import org.springframework.data.domain.Page;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 6:17 AM
 */
public interface StockService extends BaseInternalService<Stock, Long> {
    Page<Stock> index(StockFilter filter);
    String create(StockReq req);
    String addRemoveProduct(Long id, StockReq req);
    String delete(Long id);
}
