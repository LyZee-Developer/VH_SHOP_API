package com.service.BVHSHOP.service.Category.impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.Category;
import com.service.BVHSHOP.repository.CategoryRepository;
import com.service.BVHSHOP.request.Category.CategoryFilter;
import com.service.BVHSHOP.request.Category.CategoryReq;
import com.service.BVHSHOP.service.Category.CategoryService;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import org.springframework.stereotype.Service;

@Service
class CategoryServiceImpl extends BaseInternalActivateServiceImpl<Category, Long> implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository, Category.class);
        this.categoryRepository = categoryRepository;// pass it up — no getJpaRepository() override needed
    }

    @Override
    public String list(CategoryFilter id) {
        return "";
    }

    @Override
    public String create(CategoryReq req) {
        if(checkCode(req.getCode())){
            throw new ApiException("Code already existed!");
        }
        Category data = new Category();
        data.setCode(req.getCode());
        data.setName(req.getName());
        data.setEnglishName(req.getEnglishName());
        saveData(data);
        return "create successfully!";
    }

    @Override
    public String update(Long id, CategoryReq req) {
        Category data = findThrowById(id);
        data.setName(req.getName());
        data.setEnglishName(req.getEnglishName());
        return "update successfully!";
    }

    @Override
    public Boolean checkCode(String code) {
        return categoryRepository.existsByCodeAndIsActivate(code, Boolean.TRUE);
    }

    @Override
    public String delete(Long id) {
        Category data = findThrowById(id);
        data.setIsActivate(Boolean.FALSE);
        saveData(data);
        return "delete successfully!";
    }
}
