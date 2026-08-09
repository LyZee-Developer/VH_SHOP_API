package com.service.BVHSHOP.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/9/2026 11:38 PM
 */

@Entity
@Getter
@Setter
public class DataRef extends ActivateEntityModel {

    @Column(nullable = false)
    private String code;

    private String parentCode;

    @Column(nullable = false)
    private String name;
    private String englishName;
    private String description;
}
