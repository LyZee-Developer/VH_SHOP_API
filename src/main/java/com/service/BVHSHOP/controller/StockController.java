package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.request.Stock.StockFilter;
import com.service.BVHSHOP.request.Stock.StockReq;
import com.service.BVHSHOP.service.Stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 9:20 PM
 */
@RestController
@RequestMapping(RouteController.STOCK)
public class StockController {
    @Autowired
    StockService stockService;

    @PostMapping
    public ResponseEntity<?> index(@RequestBody StockFilter filter){
        return ResponseEntity.ok(ApiResponse.success(stockService.index(filter)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@ModelAttribute StockReq req){
        return ResponseEntity.ok(ApiResponse.success(stockService.create(req)));
    }

    @PostMapping("/add-remove/{id}")
    public ResponseEntity<?> addRemove(@PathVariable Long id, @ModelAttribute StockReq req){
        return ResponseEntity.ok(ApiResponse.success(stockService.addRemoveProduct(id, req)));
    }

    @DeleteMapping("/add-remove/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(stockService.delete(id)));
    }
}
