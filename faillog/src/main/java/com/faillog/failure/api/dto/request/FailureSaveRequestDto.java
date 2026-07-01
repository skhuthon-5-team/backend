package com.faillog.failure.api.dto.request;

import com.faillog.failure.domain.FailureCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record FailureSaveRequestDto(

    @NotNull(message = "사용자 정보가 필요합니다.")
    Long userId,

    @NotBlank(message = "필수 입력값이 누락되었습니다.")
    @Size(max = 1000, message = "제목은 1000자 이하로 입력해주세요.")
    String title,

    @NotNull(message = "카테고리가 올바르지 않습니다.")
    FailureCategory category,

    @NotBlank(message = "필수 입력값이 누락되었습니다.")
    @Size(max = 1000, message = "실패 상황은 1000자 이하로 입력해주세요.")
    String situation,

    @NotBlank(message = "필수 입력값이 누락되었습니다.")
    @Size(max = 1000, message = "당시 선택은 1000자 이하로 입력해주세요.")
    String choice,

    @Size(max = 1000, message = "당시 감정은 1000자 이하로 입력해주세요.")
    String emotion
    ) {
}
