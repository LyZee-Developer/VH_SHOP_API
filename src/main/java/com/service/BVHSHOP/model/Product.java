package com.service.BVHSHOP.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends ActivateEntityModel {

    @Column(nullable = false, length = 20)
    private String code;

    @Column(nullable = false, length = 30)
    private String name;

    private String englishName;

    @JoinColumn(referencedColumnName = ProductType_.ID)
    @ManyToOne
    private ProductType productType;

    @JoinColumn(referencedColumnName = ProductType_.ID)
    @ManyToOne
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_code", referencedColumnName = "code")
    @JsonIncludeProperties({DataRef_.CODE, DataRef_.NAME, DataRef_.ENGLISH_NAME, DataRef_.DESCRIPTION})
    private DataRef currency;

    private String description;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ProductPrice> productPrice = new ArrayList<>();

}
