package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.UserLogin;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:22 PM
 */
public interface UserLoginRepository extends BaseInternalActivateRepository<UserLogin, Long> {
    boolean existsByUsernameAndPasswordAndIsDisabled(String username, String password, boolean isDisabled);

    UserLogin findByUsernameAndIsDisabled(String findByName, Boolean isDisabled);
}
