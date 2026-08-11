package com.service.BVHSHOP.service.Category;

import com.service.BVHSHOP.model.Category;
import com.service.BVHSHOP.request.Category.CategoryFilter;
import com.service.BVHSHOP.request.Category.CategoryReq;
import com.service.BVHSHOP.service.BaseInternalService;
import org.springframework.data.domain.Page;

public interface CategoryService extends BaseInternalService<Category, Long>  {
    String create(CategoryReq req);
    String update(Long id, CategoryReq req);
    String delete(Long id);
    Page<Category> index(CategoryFilter filter);
    Boolean checkCode(String code);
    Category findOneThrow(Long id);
}
