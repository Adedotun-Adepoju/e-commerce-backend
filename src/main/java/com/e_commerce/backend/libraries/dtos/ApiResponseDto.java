package com.e_commerce.backend.libraries.dtos;

public record ApiResponseDto<T>(
        boolean success,
        T data,
        String message,
        Integer errorCode
) {
}
