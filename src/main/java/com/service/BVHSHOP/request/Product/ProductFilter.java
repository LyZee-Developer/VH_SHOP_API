package com.service.BVHSHOP.request.Product;

import com.service.BVHSHOP.request.BaseInternalPage;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductFilter extends BaseInternalPage {
}
