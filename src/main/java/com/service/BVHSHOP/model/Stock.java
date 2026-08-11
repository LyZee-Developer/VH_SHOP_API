package com.service.BVHSHOP.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/10/2026 11:32 PM
 */
@Entity
@Setter
@Getter
public class Stock extends ActivateEntityModel {
    private Long total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = ProductPrice_.ID)
    private ProductPrice productSale;

    @OneToMany(mappedBy = StockDetail_.STOCK, fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<StockDetail> detail = new ArrayList<>();
}
