package com.faillog.user.api;

import com.faillog.user.api.dto.request.UserUpdateRequestDto;
import com.faillog.user.api.dto.response.UserInfoResponseDto;
import com.faillog.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //내 정보 조회
    @GetMapping("/{userId}")
    public UserInfoResponseDto getMyInfo(@PathVariable Long userId) {
        return userService.getMyInfo(userId);
    }

    //프로필 수정
    @PatchMapping("/{userId}")
    public void updateProfile(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequestDto request
    ) {
        userService.updateProfile(userId, request);
    }
}
