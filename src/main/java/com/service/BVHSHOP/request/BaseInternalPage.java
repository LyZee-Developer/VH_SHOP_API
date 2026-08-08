package com.service.BVHSHOP.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseInternalPage extends BaseInternalFilter {
    private Short page;
    private Short size;
    private String sort;
}
