package com.e_commerce.backend.libraries.utils;

import com.e_commerce.backend.libraries.dtos.ApiResponseDto;

public class ResponseUtil {
    public static <T> ApiResponseDto<T> success(T data, String message) {
        return new ApiResponseDto<>(true, data, message, null);
    }

    public static ApiResponseDto<?> error(String message, int errorCode) {
        return new ApiResponseDto<>(false, null, message, errorCode);
    }
}
