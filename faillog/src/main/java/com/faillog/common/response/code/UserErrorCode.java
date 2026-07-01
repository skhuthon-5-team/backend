package com.faillog.common.response.code;

import com.faillog.common.response.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND_EXCEPTION(
            HttpStatus.NOT_FOUND,
            "해당 사용자가 없습니다, userId = "
    );

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
