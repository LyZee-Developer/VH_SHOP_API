package com.service.BVHSHOP.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BaseInternalFilter {
    private List<String> fetch;
}
