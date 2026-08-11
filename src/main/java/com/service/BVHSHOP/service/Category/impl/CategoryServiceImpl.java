package com.service.BVHSHOP.service.Category.impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.Category;
import com.service.BVHSHOP.repository.CategoryRepository;
import com.service.BVHSHOP.request.Category.CategoryFilter;
import com.service.BVHSHOP.request.Category.CategoryReq;
import com.service.BVHSHOP.service.Category.CategoryService;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.specification.CategorySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
class CategoryServiceImpl extends BaseInternalActivateServiceImpl<Category, Long> implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository, Category.class);
        this.categoryRepository = categoryRepository;// pass it up — no getJpaRepository() override needed
    }

    @Override
    public Page<Category> index(CategoryFilter filter) {
        Pageable page = PageRequest.of(filter.getPage(),filter.getSize());
        Specification<Category> specification = new CategorySpecification(filter);
        return findAllSpePageFetch(specification, page, filter.getFetch());
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
    public Category findOneThrow(Long id) {
        return findThrowById(id);
    }

    @Override
    public String delete(Long id) {
        Category data = findThrowById(id);
        data.setIsActivate(Boolean.FALSE);
        saveData(data);
        return "delete successfully!";
    }
}
