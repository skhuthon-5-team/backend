package com.faillog.failure.api.dto.response;

import com.faillog.failure.domain.Failure;
import com.faillog.failure.domain.FailureCategory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FailureInfoResponseDto(
        String title,
        FailureCategory category,
        String situation,
        String choice,
        String emotion,
        String writer,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static FailureInfoResponseDto from(Failure failure) {
        return FailureInfoResponseDto.builder()
                .title(failure.getTitle())
                .category(failure.getCategory())
                .situation(failure.getSituation())
                .choice(failure.getChoice())
                .emotion(failure.getEmotion())
                .writer(failure.getUser().getNickname())
                .createdAt(failure.getCreatedAt())
                .updatedAt(failure.getUpdatedAt())
                .build();
    }
}
