package com.faillog.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum FailureErrorCode {

    /**
     * 404 NOT FOUND (찾을 수 없음)
     */
    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. userId = "),
    FAILURE_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 실패담이 없습니다. failureId = "),

    /**
     * 500 INTERNAL SERVER ERROR (내부 서버 에러)
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다");

    private final HttpStatus httpStatus;    // HTTP 상태 코드를 스프링에서 쉽게 작성하기 위한 enum값들의 모임
    private final String message;           // 에러 메세지


    public int getHttpStatusCode() {        // HTTP 상태 코드에서 404와 같은 숫자 값만 반환해 주기 위한 메소드
        return httpStatus.value();
    }
}

