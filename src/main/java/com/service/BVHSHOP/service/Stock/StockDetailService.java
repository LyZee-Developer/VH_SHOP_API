package com.service.BVHSHOP.service.Stock;

import com.service.BVHSHOP.model.Stock;
import com.service.BVHSHOP.model.StockDetail;
import com.service.BVHSHOP.request.Stock.StockReq;
import com.service.BVHSHOP.service.BaseInternalService;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 6:17 AM
 */
public interface StockDetailService extends BaseInternalService<StockDetail,Long> {
    void save(Stock pro, StockReq req, Long total);
}
