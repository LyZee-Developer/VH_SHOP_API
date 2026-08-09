package com.service.BVHSHOP.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseInternalPage extends BaseInternalFilter {
    private Short page = 0;
    private Short size = 10;
    private String sort;
}
