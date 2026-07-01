package com.faillog.badge.application;

import com.faillog.badge.api.dto.response.BadgeInfoResponseDto;
import com.faillog.badge.domain.Badge;
import com.faillog.badge.domain.UserBadge;
import com.faillog.badge.domain.repository.BadgeRepository;
import com.faillog.badge.domain.repository.UserBadgeRepository;
import com.faillog.user.domain.User;
import com.faillog.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final UserRepository userRepository;

    //전체 배지 조회
    public List<BadgeInfoResponseDto> getBadges() {

        return badgeRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    //특정 사용자의 배지 조회
    public List<BadgeInfoResponseDto> getUserBadges(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return userBadgeRepository.findByUser(user)
                .stream()
                .map(UserBadge::getBadge)
                .map(this::toDto)
                .toList();
    }

    private BadgeInfoResponseDto toDto(Badge badge) {
        return BadgeInfoResponseDto.builder()
                .badgeId(badge.getBadgeId())
                .badgeName(badge.getBadgeName())
                .description(badge.getDescription())
                .imageUrl(badge.getImageUrl())
                .condition(badge.getCondition())
                .build();
    }

    @Transactional
    public void giveBadge(Long userId, Long badgeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        Badge badge = badgeRepository.findById(badgeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 배지입니다."));

        if (userBadgeRepository.findByUserAndBadge(user, badge).isPresent()) {
            return;
        }

        userBadgeRepository.save(
                UserBadge.builder()
                        .user(user)
                        .badge(badge)
                        .earnedAt(LocalDateTime.now())
                        .build()
        );
    }
}
