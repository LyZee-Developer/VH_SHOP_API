package com.service.BVHSHOP.service.ProductType.impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.ProductType;
import com.service.BVHSHOP.repository.ProductTypeRepository;
import com.service.BVHSHOP.request.ProductType.ProductTypeFilter;
import com.service.BVHSHOP.request.ProductType.ProductTypeReq;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.service.ProductType.ProductTypeService;
import com.service.BVHSHOP.specification.ProductTypeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
class ProductTypeServiceImpl extends BaseInternalActivateServiceImpl<ProductType, Long> implements ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository) {
        super(productTypeRepository, ProductType.class);
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public Page<ProductType> index(ProductTypeFilter filter) {
        Pageable page = PageRequest.of(filter.getPage(), filter.getSize());
        Specification<ProductType> spec = new ProductTypeSpecification(filter);
        return findAllSpePageFetch(spec, page, filter.getFetch());
    }

    @Override
    public String create(ProductTypeReq req) {
        if (checkCode(req.getCode())) {
            throw new ApiException("code already existed!");
        }
        ProductType save = new ProductType();
        save.setName(req.getName());
        save.setEnglishName(req.getEnglishName());
        save.setCode(req.getCode());
        saveData(save);
        return "Create successfully";
    }

    @Override
    public String delete(Long id) {
        ProductType data = findThrowById(id);
        data.setIsActivate(Boolean.FALSE);
        saveData(data);
        return "delete successfully!";
    }

    @Override
    public ProductType findOne(Long id) {
        return findThrowById(id);
    }

    @Override
    public boolean checkCode(String code) {
        return productTypeRepository.existsByCodeAndIsActivateTrue(code);
    }

    @Override
    public String update(Long id, ProductTypeReq req) {
        ProductType data = findThrowById(id);
        data.setName(req.getName());
        data.setEnglishName(req.getEnglishName());
        saveData(data);
        return "update successfully!";
    }
}
