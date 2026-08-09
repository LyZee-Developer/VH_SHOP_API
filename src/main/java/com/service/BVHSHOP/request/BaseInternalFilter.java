package com.service.BVHSHOP.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public abstract class BaseInternalFilter {
    private String search;
    private List<String> fetch;
}
