package com.faillog.common.response;

public interface ErrorCode {
    int getHttpStatusCode();
    String getMessage();
}
