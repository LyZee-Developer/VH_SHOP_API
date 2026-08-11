package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.request.Product.ProductFilter;
import com.service.BVHSHOP.request.Product.ProductReq;
import com.service.BVHSHOP.service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/10/2026 6:19 AM
 */

@RestController
@RequestMapping(RouteController.PRODUCT)
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> index(@RequestBody ProductFilter filter) {
        return ResponseEntity.ok(ApiResponse.success(productService.index(filter)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductReq req) {
        return ResponseEntity.ok(ApiResponse.success(productService.create(req)));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductReq req) {
        return ResponseEntity.ok(ApiResponse.success(productService.update(id, req)));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(productService.delete(id)));
    }
}
