package com.service.BVHSHOP.exception;
/**
 * @Author : Ly LeangSeng
 * @Email : lyleangseng712@gmail.com
 * @Date : 8/11/2026 6:19 AM
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
