package com.service.BVHSHOP.request.Stock;

import com.service.BVHSHOP.model.ProductPrice;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 6:21 AM
 */
@Getter
@Setter
public class StockReq {
    private Long qty;
    private Long productSaleId;
    private String remark;
    private String stockType; //IN, OUT

}
