package com.service.BVHSHOP.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class ProductType extends ActivateEntityModel {
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String englishName;

    @OneToMany(mappedBy = ProductTypeItem_.PRODUCT_TYPE, fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ProductTypeItem> items = new ArrayList<>();
}
