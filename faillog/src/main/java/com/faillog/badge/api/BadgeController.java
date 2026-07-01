package com.faillog.badge.api;

import com.faillog.badge.api.dto.response.BadgeInfoResponseDto;
import com.faillog.badge.application.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    //전체 배지 조회
    @GetMapping
    public List<BadgeInfoResponseDto> getBadges() {
        return badgeService.getBadges();
    }

    //특정 사용자 배지 조회
    @GetMapping("/{userId}")
    public List<BadgeInfoResponseDto> getUserBadges(@PathVariable Long userId) {
        return badgeService.getUserBadges(userId);
    }
}
