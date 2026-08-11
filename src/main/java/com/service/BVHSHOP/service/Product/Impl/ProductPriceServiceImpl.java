package com.service.BVHSHOP.service.Product.Impl;

import com.service.BVHSHOP.exception.ApiException;
import com.service.BVHSHOP.model.Product;
import com.service.BVHSHOP.model.ProductPrice;
import com.service.BVHSHOP.repository.ProductPriceRepository;
import com.service.BVHSHOP.request.Product.ProductReq;
import com.service.BVHSHOP.service.Impl.BaseInternalActivateServiceImpl;
import com.service.BVHSHOP.service.Product.ProductPriceService;
import com.service.BVHSHOP.service.ProductTypeItem.ProductTypeItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:41 PM
 */

@Service
public class ProductPriceServiceImpl extends BaseInternalActivateServiceImpl<ProductPrice, Long> implements ProductPriceService {

    @Autowired
    ProductTypeItemService productTypeItemService;

    @Autowired
    ProductPriceRepository productPriceRepository;

    public ProductPriceServiceImpl(ProductPriceRepository repository) {
        super(repository, ProductPrice.class);
        productPriceRepository = repository;
    }

    @Override
    public void saveAllPrice(List<ProductReq.Price> productPrice, Product product) {
        boolean isKHR = product.getCurrency().getCode().equals("KHR");
        productPriceRepository.saveAll(productPrice.stream().map(item -> {

            if(isKHR && item.getAmount() < 100){
                throw new ApiException("%.0f៛ Invalid amount with currency KHR".formatted(item.getAmount()));
            }
            ProductPrice price = Optional.ofNullable(item.getId())
                    .flatMap(productPriceRepository::findById)
                    .orElseGet(ProductPrice::new);
            price.setAmount(item.getAmount());
            price.setProduct(product);
            price.setProductItem(productTypeItemService.findById(item.getProductItemId()).orElse(null));
            return price;
        }).toList());
    }
}
