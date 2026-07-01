package com.faillog.failure.api.dto.request;

import com.faillog.failure.domain.FailureCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FailureUpdateRequestDto(

        @NotBlank(message = "필수 입력값이 누락되었습니다.")
        String title,

        @NotNull(message = "카테고리가 올바르지 않습니다.")
        FailureCategory category,

        @NotBlank(message = "필수 입력값이 누락되었습니다.")
        String situation,

        @NotBlank(message = "필수 입력값이 누락되었습니다.")
        String choice,

        String emotion
) {
}
