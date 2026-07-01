package com.faillog.common.template;

import com.faillog.common.response.ErrorCode;
import com.faillog.common.response.SuccessCode;


public record ApiResTemplate<T>(
        int code,
        String message,
        T data
) {

    // 데이터 없는 성공 응답
    public static ApiResTemplate<Void> successWithNoContent(SuccessCode successCode) {
        return new ApiResTemplate<>(
                successCode.getHttpStatusCode(),
                successCode.getMessage(),
                null
        );
    }

    // 데이터 포함 성공 응답
    public static <T> ApiResTemplate<T> success(SuccessCode successCode, T data) {
        return new ApiResTemplate<>(
                successCode.getHttpStatusCode(),
                successCode.getMessage(),
                data
        );
    }

    // 에러 응답
    public static ApiResTemplate<Void> error(ErrorCode errorCode) {
        return new ApiResTemplate<>(
                errorCode.getHttpStatusCode(),
                errorCode.getMessage(),
                null
        );
    }

    // 커스텀 메시지 에러 응답(메소드 오버로딩)
    public static ApiResTemplate<Void> error(ErrorCode errorCode, String customMessage) {
        return new ApiResTemplate<>(
                errorCode.getHttpStatusCode(),
                customMessage,
                null
        );
    }
}
