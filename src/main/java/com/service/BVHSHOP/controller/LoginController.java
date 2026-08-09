package com.service.BVHSHOP.controller;

import com.service.BVHSHOP.apiResponse.ApiResponse;
import com.service.BVHSHOP.constant.RouteController;
import com.service.BVHSHOP.request.UserLogin.UserLoginReq;
import com.service.BVHSHOP.service.UserLogin.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RouteController.USER_LOGIN)
public class LoginController {
    @Autowired
    UserLoginService userLoginService;

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody UserLoginReq model){
        String data = userLoginService.createUserLogin(model);
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserLoginReq model){
        return ResponseEntity.ok(ApiResponse.success(userLoginService.login(model)));
    }
}
