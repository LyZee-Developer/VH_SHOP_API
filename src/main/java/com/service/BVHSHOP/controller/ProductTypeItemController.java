package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.request.ProductType.ProductTypeReq;
import com.service.BVHSHOP.request.ProductTypeItem.ProductTypeItemReq;
import com.service.BVHSHOP.service.ProductType.ProductTypeService;
import com.service.BVHSHOP.service.ProductTypeItem.ProductTypeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RouteController.PRODUCT_TYPE_ITEM)
public class ProductTypeItemController {

    @Autowired
    ProductTypeItemService productTypeItemService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductTypeItemReq req){
        return ResponseEntity.ok(ApiResponse.success(productTypeItemService.create(req)));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody ProductTypeItemReq req){
        return ResponseEntity.ok(ApiResponse.success(productTypeItemService.update(id, req)));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(productTypeItemService.delete(id)));
    }
}
