package com.service.BVHSHOP.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends ActivateEntityModel{
    private String name;
    private String englishName;
    private String code;
}
