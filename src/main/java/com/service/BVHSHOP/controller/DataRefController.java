package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.model.DataRef;
import com.service.BVHSHOP.service.DataRef.DataRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/10/2026 6:33 AM
 */
@RestController
@RequestMapping(RouteController.DATA_REF)
public class DataRefController {
    @Autowired
    DataRefService dataRefService;

    @GetMapping("/find/{code}")
    public ResponseEntity<Object> findByCode(@PathVariable String code) {
        DataRef data = dataRefService.findByCodeThrow(code);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping("/child-parent/{parentCode}")
    public ResponseEntity<Object> childParent(@PathVariable String parentCode) {
        return ResponseEntity.ok(ApiResponse.success(dataRefService.getListByParentCode(parentCode)));
    }
}
