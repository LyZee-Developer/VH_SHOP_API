package com.service.BVHSHOP.dto;

import com.service.BVHSHOP.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/15/2026 11:48 AM
 */
@Setter
@Getter
public class ProductDTO extends Product {
    private String code;
    private String name;
    private String englishName;
    private ProductType productType;
    private Category category;
    private DataRef currency;
    private String description;
    private List<ProductPrice> productPrice = new ArrayList<>();
    private List<String> images = new ArrayList<>();

    public ProductDTO(Product product){
        BeanUtils.copyProperties(product,this);
    }

    public ProductDTO(Product product,List<String> images){
        BeanUtils.copyProperties(product,this);
        this.images = images;
    }
}
