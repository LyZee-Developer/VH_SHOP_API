package com.service.BVHSHOP.request.ProductTypeItem;

import com.service.BVHSHOP.request.BaseInternalFilter;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductTypeItemFilter{
    private Long productTypeId;
}
