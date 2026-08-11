package com.service.BVHSHOP.service.Stock.Impl;

import com.service.BVHSHOP.model.ProductPrice;
import com.service.BVHSHOP.model.Stock;
import com.service.BVHSHOP.model.StockDetail;
import com.service.BVHSHOP.repository.BaseInternalActivateRepository;
import com.service.BVHSHOP.repository.StockDetailRepository;
import com.service.BVHSHOP.request.Stock.StockReq;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.service.Stock.StockDetailService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 6:19 AM
 */
@Service
class StockDetailServiceImpl extends BaseInternalActivateServiceImpl<StockDetail, Long> implements StockDetailService {
    private final StockDetailRepository stockDetailRepository;

    public StockDetailServiceImpl(StockDetailRepository stockDetailRepository) {
        super(stockDetailRepository, StockDetail.class);
        this.stockDetailRepository = stockDetailRepository;
    }

    @Override
    public void save(Stock stock, StockReq req, Long total) {
        StockDetail det = new StockDetail();

        det.setStockType(req.getStockType());
        det.setRemark(req.getRemark());
        det.setStock(stock);
        det.setDate(LocalDateTime.now());
        det.setQty(total);
        saveData(det);
    }
}
