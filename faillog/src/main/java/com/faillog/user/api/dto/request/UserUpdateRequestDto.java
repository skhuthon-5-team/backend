package com.faillog.user.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    // 닉네임 변경
    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;

    // 프로필 이미지 변경
    private String profileImage;
}
