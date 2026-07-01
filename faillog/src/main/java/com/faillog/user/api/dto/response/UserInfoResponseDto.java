package com.faillog.user.api.dto.response;

import com.faillog.user.domain.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserInfoResponseDto {

    private Long userId;
    private String email;
    private String nickname;
    private String profileImage;
    private Provider provider;
}