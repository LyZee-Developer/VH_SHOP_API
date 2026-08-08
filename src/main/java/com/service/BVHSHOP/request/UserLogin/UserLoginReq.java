package com.service.BVHSHOP.request.UserLogin;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginReq {
    @NotBlank(message = "username required!")
    private String username;

    @NotBlank(message = "username required!")
    private String password;
}
