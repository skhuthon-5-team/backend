package com.faillog.auth.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDto {

    private String accessToken;
    private String refreshToken;

    private Long userId;
    private String email;
    private String nickname;

}