package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long> {
    boolean existsByUsernameAndPasswordAndIsDisabled(String username, String password, boolean isDisabled);
    UserLogin findByUsernameAndIsDisabled(String findByName, Boolean isDisabled);
}
