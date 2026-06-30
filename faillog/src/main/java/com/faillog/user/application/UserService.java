package com.faillog.user.application;

import com.faillog.user.api.dto.request.UserUpdateRequestDto;
import com.faillog.user.api.dto.response.UserInfoResponseDto;
import com.faillog.user.domain.User;
import com.faillog.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    // UserRepository를 주입받아 DB와 통신
    private final UserRepository userRepository;

    //내 정보 조회
    public UserInfoResponseDto getMyInfo(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
                //throw new BusinessException(UserErrorCode.USER_001);

        return UserInfoResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImage(user.getProfileImage())
                .provider(user.getProvider())
                .build();
    }

    //프로필 수정
    public void updateProfile(Long userId, UserUpdateRequestDto request) {
        User user = userRepository.findById(userId) .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        user.updateProfile(
                request.getNickname(),
                request.getProfileImage()
        );
        // TODO : User 엔티티에 프로필 수정 메서드 추가 후 수정 예정
    }
}