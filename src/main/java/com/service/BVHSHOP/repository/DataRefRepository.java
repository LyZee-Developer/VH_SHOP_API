package com.service.BVHSHOP.repository;

import com.service.BVHSHOP.model.DataRef;

import java.util.List;
import java.util.Optional;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 11:42 PM
 */
public interface DataRefRepository extends BaseInternalActivateRepository<DataRef, Long>{
    Optional<DataRef> findByCodeAndIsActivateTrue(String code);
    List<DataRef> findByParentCodeAndIsActivateTrue(String code);
}
