package com.e_commerce.backend.dtos.responses;

public record ApiResponseDto<T>(
        boolean success,
        T data,
        String message,
        Integer errorCode
) {
}
