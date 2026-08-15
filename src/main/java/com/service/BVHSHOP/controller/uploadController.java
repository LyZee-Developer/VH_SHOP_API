package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.request.UploadImage;
import com.service.BVHSHOP.service.file.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/12/2026 9:21 PM
 */
@RestController
@RequestMapping(RouteController.UPLOAD)
public class uploadController {
    @Autowired
    UploadService uploadService;

    @PostMapping
    public ResponseEntity<?> index(@ModelAttribute UploadImage req) throws IOException {
        return ResponseEntity.ok(ApiResponse.success(uploadService.saveMultiFile(req.getFiles(),"car",0L,"test")));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(uploadService.deleteImage(id)));
    }
}
