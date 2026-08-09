package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.UserLogin;

public interface UserLoginRepository extends BaseInternalActivateRepository<UserLogin, Long> {
    boolean existsByUsernameAndPasswordAndIsDisabled(String username, String password, boolean isDisabled);
    UserLogin findByUsernameAndIsDisabled(String findByName, Boolean isDisabled);
}
