package com.service.BVHSHOP.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends ActivateEntityModel{
    @Column(nullable = false)
    private String name;
    private String englishName;
    @Column(nullable = false)
    private String code;
}
