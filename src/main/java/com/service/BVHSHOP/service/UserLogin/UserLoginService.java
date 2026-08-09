package com.service.BVHSHOP.service.UserLogin;

import com.service.BVHSHOP.apiResponse.LoginResponse;
import com.service.BVHSHOP.model.UserLogin;
import com.service.BVHSHOP.request.UserLogin.UserLoginReq;
import com.service.BVHSHOP.service.BaseInternalService;

public interface UserLoginService extends BaseInternalService<UserLogin, Long> {
    String createUserLogin(UserLoginReq req);
    LoginResponse login(UserLoginReq req);
}
