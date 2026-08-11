package com.service.BVHSHOP.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 10:22 PM
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @JoinColumn(referencedColumnName = ProductTypeItem_.ID)
    @ManyToOne
    private ProductTypeItem productItem;

    private Double amount;
    private Boolean isActivate = true;

}
