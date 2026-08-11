package com.service.BVHSHOP.service.DataRef.Impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.DataRef;
import com.service.BVHSHOP.repository.BaseInternalActivateRepository;
import com.service.BVHSHOP.repository.DataRefRepository;
import com.service.BVHSHOP.service.DataRef.DataRefService;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/10/2026 6:24 AM
 */
@Service
class DataRefServiceImpl extends BaseInternalActivateServiceImpl<DataRef, Long> implements DataRefService {
    private final DataRefRepository dataRefRepository;
    public DataRefServiceImpl(DataRefRepository dataRefRepository) {
        super(dataRefRepository, DataRef.class);
        this.dataRefRepository = dataRefRepository;
    }

    @Override
    public DataRef findByCodeThrow(String code) {
        return dataRefRepository.findByCodeAndIsActivateTrue(code).orElseThrow(()-> new ApiException("DataRef Code not found!"));
    }

    @Override
    public List<DataRef> getListByParentCode(String code) {
        return dataRefRepository.findByParentCodeAndIsActivateTrue(code);
    }
}
