package com.service.BVHSHOP.service.ProductTypeItem.impl;

import com.service.BVHSHOP.model.ProductType;
import com.service.BVHSHOP.model.ProductTypeItem;
import com.service.BVHSHOP.repository.ProductTypeItemRepository;
import com.service.BVHSHOP.request.ProductTypeItem.ProductTypeItemReq;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.service.ProductType.ProductTypeService;
import com.service.BVHSHOP.service.ProductTypeItem.ProductTypeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductTypeItemServiceImpl extends BaseInternalActivateServiceImpl<ProductTypeItem, Long> implements ProductTypeItemService {

    public ProductTypeItemServiceImpl(ProductTypeItemRepository productTypeItemRepository) {
        super(productTypeItemRepository, ProductTypeItem.class);
    }

    @Autowired
    ProductTypeService productTypeService;

    @Override
    public List<ProductTypeItem> listItem(ProductTypeItemReq req) {
        return List.of();
    }

    @Override
    public String create(ProductTypeItemReq req) {
        ProductType type = productTypeService.findOne(req.getProductTypeId());

        ProductTypeItem item = new ProductTypeItem();
        item.setProductType(type);
        item.setName(req.getName());
        item.setEnglishName(req.getEnglishName());
        item.setActivate(req.isActivate());
        saveData(item);
        return "create success!";
    }

    @Override
    public String update(Long id, ProductTypeItemReq req) {
        ProductType type = productTypeService.findOne(req.getProductTypeId());

        ProductTypeItem item = findThrowById(id);
        item.setName(req.getName());
        item.setProductType(type);
        item.setEnglishName(req.getEnglishName());
        item.setActivate(req.isActivate());
        saveData(item);
        return "update success!";
    }

    @Override
    public String delete(Long id) {
        ProductTypeItem item = findThrowById(id);
        item.setActivate(Boolean.FALSE);
        saveData(item);
        return "";
    }
}
