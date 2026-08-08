package com.service.BVHSHOP.apiResponse;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginResponse {
    private String token;
    private String tokenType;
    private Long expiresIn;
}
