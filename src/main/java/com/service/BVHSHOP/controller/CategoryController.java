package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.request.Category.CategoryFilter;
import com.service.BVHSHOP.request.Category.CategoryReq;
import com.service.BVHSHOP.service.Category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RouteController.CATEGORY)
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/list")
    public ResponseEntity<Object> index(@RequestBody CategoryFilter filter){
        return ResponseEntity.ok(ApiResponse.success(categoryService.list(filter)));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CategoryReq model){
        return ResponseEntity.ok(ApiResponse.success(categoryService.create(model)));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id, @RequestBody CategoryReq model){
        return ResponseEntity.ok(ApiResponse.success(categoryService.update(id, model)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(ApiResponse.success(categoryService.delete(id)));
    }

    @GetMapping("/check-code")
    public ResponseEntity<Object> checkCode(@RequestParam String name){
        return ResponseEntity.ok(ApiResponse.success(categoryService.checkCode(name)));
    }
}
