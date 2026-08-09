package com.service.BVHSHOP.request.ProductType;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductTypeReq {
    @NotBlank(message = "name is required!")
    private String name;

    @NotBlank(message = "code is required!")
    private String code;

    private String englishName;
}
