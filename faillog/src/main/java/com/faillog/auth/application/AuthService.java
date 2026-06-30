package com.faillog.auth.application;

import com.faillog.auth.api.dto.request.LoginRequestDto;
import com.faillog.auth.api.dto.request.SignupRequestDto;
import com.faillog.auth.api.dto.response.LoginResponseDto;
import com.faillog.user.domain.Provider;
import com.faillog.user.domain.User;
import com.faillog.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    //회원 가입
    public void signup(SignupRequestDto request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .provider(Provider.LOCAL)
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }

    //로그인
    public LoginResponseDto login(LoginRequestDto request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return LoginResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .build();
    }

    //로그아웃
    public void logout() {

    }

    //비밀번호 재설정
    public void resetPassword() {

    }
}