package com.service.BVHSHOP.service.Stock.Impl;

import com.service.BVHSHOP.constant.StockConst;
import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.ProductPrice;
import com.service.BVHSHOP.model.Stock;
import com.service.BVHSHOP.repository.StockRepository;
import com.service.BVHSHOP.request.Stock.StockFilter;
import com.service.BVHSHOP.request.Stock.StockReq;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.service.Product.ProductPriceService;
import com.service.BVHSHOP.service.Stock.StockDetailService;
import com.service.BVHSHOP.service.Stock.StockService;
import com.service.BVHSHOP.specification.StockSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 6:19 AM
 */
@Service
class StockImpl extends BaseInternalActivateServiceImpl<Stock, Long> implements StockService {
    private final StockRepository stockRepository;

    @Autowired
    ProductPriceService productPriceService;

    @Autowired
    StockDetailService stockDetailService;

    public StockImpl(StockRepository stockRepository) {
        super(stockRepository, Stock.class);
        this.stockRepository = stockRepository;
    }

    @Override
    public Page<Stock> index(StockFilter filter) {
        Pageable page = PageRequest.of(filter.getPage(), filter.getSize());
        Specification<Stock> spec = new StockSpecification(filter);
        return findAllSpePageFetch(spec, page, filter.getFetch());
    }

    @Override
    @Transactional
    public String create(StockReq req) {
        ProductPrice product = productPriceService.findThrowById(req.getProductSaleId());
        boolean isExisted = stockRepository.existsByProductSaleIdAndIsActivateTrue(req.getProductSaleId());
        if (isExisted) {
            throw new ApiException("The product, we have add already!");
        }
        Stock stk = new Stock();
        stk.setTotal(req.getQty());
        stk.setProductSale(product);
        saveData(stk);

        stockDetailService.save(stk, req, stk.getTotal());
        return "create successfully!";
    }


    @Override
    public String addRemoveProduct(Long id, StockReq req) {

        ProductPrice product = productPriceService.findThrowById(req.getProductSaleId());
        Stock stk = findThrowById(id);
        String type = "add";

        if (req.getStockType().equals(StockConst.IN)) {
            var sumQty = stk.getTotal() + req.getQty();
            stk.setTotal(sumQty);
        } else {
            var remain = stk.getTotal() - req.getQty();
            if (remain < 0) {
                throw new ApiException("Your product doesn't have enough for sell!");
            }
            stk.setTotal(remain);
            type = "remove";
        }
        stk.setProductSale(product);
        saveData(stk);

        stockDetailService.save(stk, req, stk.getTotal());
        return "%s successfully!".formatted(type);
    }

    @Override
    public String delete(Long id) {
        Stock stock = findThrowById(id);
        stock.setIsActivate(Boolean.FALSE);
        saveData(stock);
        return "delete success!";
    }

}
