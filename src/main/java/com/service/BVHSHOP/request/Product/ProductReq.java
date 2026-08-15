package com.service.BVHSHOP.request.Product;

import com.service.BVHSHOP.request.BaseUploadFile;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReq {
    @NotBlank(message = "code is required")
    private String code;

    @NotBlank(message = "name is required")
    private String name;

    private String englishName;
    private String currencyCode;
    private Long productTypeId;

    @NotNull(message = "category is required")
    private Long categoryId;

    private List<Price> prices = new ArrayList<>();

    @Getter
    @Setter
    public static class Price {
        private Long id;
        @NotNull(message = "Product item id is required")
        private Long productItemId;
        private Double amount;
    }
}
