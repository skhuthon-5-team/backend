package com.faillog.failure.api.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FailureListResponseDto(
        List<FailureInfoResponseDto> failures
) {
    public static FailureListResponseDto from(List<FailureInfoResponseDto> failures) {
        return FailureListResponseDto.builder()
                .failures(failures)
                .build();
    }
}
