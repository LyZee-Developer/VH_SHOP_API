package com.service.BVHSHOP.request.ProductTypeItem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductTypeItemReq {
    private Long productTypeId;
    @NotBlank(message = "name is required!")
    private String name;
    private String englishName;
    private boolean isActivate = true;
}
