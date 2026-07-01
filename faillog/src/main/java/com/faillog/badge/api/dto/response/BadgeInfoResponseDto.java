package com.faillog.badge.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class BadgeInfoResponseDto {

    private Long badgeId;
    private String badgeName;
    private String description;
    private String imageUrl;
    private String condition;
}
