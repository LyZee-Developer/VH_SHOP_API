package com.service.BVHSHOP.apiResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ApiResponse<T> {
        private String status;
        private T data;
        private Object errors;
        private LocalDateTime timestamp = LocalDateTime.now();

        public ApiResponse(String status, T data, Object errors) {
            this.status = status;
            this.data = data;
            this.errors = errors;
        }

        public static <T> ApiResponse<T> success(T data) {
            return new ApiResponse<>("Success", data, null);
        }

        public static <T> ApiResponse<T> fail(Object error) {
            return new ApiResponse<>("Fail", null, error);
        }
}
