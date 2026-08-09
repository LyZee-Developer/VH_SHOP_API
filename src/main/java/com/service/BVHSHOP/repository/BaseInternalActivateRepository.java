package com.service.BVHSHOP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:22 PM
 */
@NoRepositoryBean
public interface BaseInternalActivateRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    Optional<T> findByIdAndIsActivateTrue(ID id);

}
