package com.service.BVHSHOP.service.UserLogin.Impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.UserLogin;
import com.service.BVHSHOP.repository.UserLoginRepository;
import com.service.BVHSHOP.request.UserLogin.UserLoginReq;
import com.service.BVHSHOP.service.Impl.BaseInternalServiceImpl;
import com.service.BVHSHOP.service.UserLogin.UserLoginService;
import com.service.BVHSHOP.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
class UserLoginServiceImpl extends BaseInternalServiceImpl<UserLogin, Long> implements UserLoginService {

    @Autowired
    UserLoginRepository userLoginRepository;

    public UserLoginServiceImpl(UserLoginRepository userLoginRepository) {
        super(userLoginRepository);
        this.userLoginRepository = userLoginRepository;
    }


    @Override
    public String createUserLogin(UserLoginReq req) {
        if(isExistedUsername(req.getUsername(), req.getPassword())){
            throw new ApiException("Username have already existed");
        }
        UserLogin data = new UserLogin();
        data.setUsername(req.getUsername());
        data.setPassword(PasswordUtil.encode(req.getPassword()));
        saveData(data);
        return "create account successfully!";
    }

    private boolean isExistedUsername(String name, String password){
        UserLogin user = userLoginRepository.findByUsernameAndIsDisabled(name, Boolean.FALSE);
        if(ObjectUtils.isEmpty(user)) return false;
        return PasswordUtil.matches(password, user.getPassword());
    }

    @Override
    public String login(UserLoginReq req) {
        UserLogin user = userLoginRepository.findByUsernameAndIsDisabled(req.getUsername(), Boolean.FALSE);
        boolean isMatch = PasswordUtil.matches(req.getPassword(), user.getPassword());
        if (!ObjectUtils.isEmpty(user) && !isMatch) {
            if (user.getAttempt() < 3)
                user.setAttempt((short) (user.getAttempt() + 1));
            else {
                throw new ApiException("You user account have been disabled!");
            }
        } else if (ObjectUtils.isEmpty(user)) {
            throw new ApiException("User account not found!");
        }

        return "Login success";
    }
}
