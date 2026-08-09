package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.request.ProductType.ProductTypeFilter;
import com.service.BVHSHOP.request.ProductType.ProductTypeReq;
import com.service.BVHSHOP.service.ProductType.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RouteController.PRODUCT_TYPE)
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @PostMapping
    public ResponseEntity<?> index(@RequestBody ProductTypeFilter filter){
        return ResponseEntity.ok(ApiResponse.success(productTypeService.index(filter)));
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductTypeReq req){
        return ResponseEntity.ok(ApiResponse.success(productTypeService.create(req)));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody ProductTypeReq req){
        return ResponseEntity.ok(ApiResponse.success(productTypeService.update(id, req)));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(productTypeService.delete(id)));
    }
}
