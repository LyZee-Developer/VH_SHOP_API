package com.service.BVHSHOP.service.Product.Impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.Category;
import com.service.BVHSHOP.model.Product;
import com.service.BVHSHOP.model.ProductType;
import com.service.BVHSHOP.repository.ProductRepository;
import com.service.BVHSHOP.request.Product.ProductFilter;
import com.service.BVHSHOP.request.Product.ProductReq;
import com.service.BVHSHOP.service.Category.CategoryService;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.service.Product.ProductPriceService;
import com.service.BVHSHOP.service.Product.ProductService;
import com.service.BVHSHOP.service.ProductType.ProductTypeService;
import com.service.BVHSHOP.service.ProductTypeItem.ProductTypeItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
class ProductServiceImpl extends BaseInternalActivateServiceImpl<Product, Long> implements ProductService {

    private final ProductRepository productRepository;
    @Autowired
    ProductTypeService productTypeService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductPriceService productPriceService;

    @Autowired
    ProductTypeItemService productTypeItemService;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository, Product.class);
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> index(ProductFilter filter) {
        return null;
    }

    @Override
    @Transactional
    public String create(ProductReq req) {
        if(checkCode(req.getCode())){
            throw new ApiException("code already existed!");
        }

        if(req.getPrices().isEmpty()){
            throw new ApiException("Please enter price to each items");
        }

        Category cate = categoryService.findOneThrow(req.getCategoryId());

        Product pro = new Product();
        pro.setProductType(productTypeService.findById(req.getProductTypeId()).orElse(null));
        pro.setName(req.getName());
        pro.setEnglishName(req.getEnglishName());
        pro.setCode(req.getCode());
        pro.setCategory(cate);
        saveData(pro);

        // save product Item
        productPriceService.saveAllPrice(req.getPrices(), pro);
        return "create success";
    }

    @Override
    @Transactional
    public String update(Long id, ProductReq req) {
        Category cate = categoryService.findOneThrow(req.getCategoryId());

        Product pro = findThrowById(id);
        pro.setProductType(Optional.ofNullable(req.getProductTypeId()).map(productTypeService::findThrowById).orElse(null));
        pro.setName(req.getName());
        pro.setEnglishName(req.getEnglishName());
        pro.setCategory(cate);
        saveData(pro);
        return "update success";
    }

    @Override
    public boolean checkCode(String code) {
        return productRepository.existsByCodeAndIsActivateTrue(code);
    }

    @Override
    public String delete(Long id) {
        Product pro = findThrowById(id);
        pro.setIsActivate(Boolean.FALSE);
        saveData(pro);
        return "delete successfully!";
    }
}
