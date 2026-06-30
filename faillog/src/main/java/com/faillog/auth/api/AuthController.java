package com.faillog.auth.api;

import com.faillog.auth.api.dto.request.LoginRequestDto;
import com.faillog.auth.api.dto.request.SignupRequestDto;
import com.faillog.auth.api.dto.response.LoginResponseDto;
import com.faillog.auth.application.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //POST /api/auth/signup
    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequestDto request) {
        authService.signup(request);
    }

    //POST /api/auth/login
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return authService.login(request);
    }

    //POST /api/auth/logout
    @PostMapping("/logout")
    public void logout() {
        authService.logout();
    }

    //POST /api/auth/password/reset
    @PostMapping("/password/reset")
    public void resetPassword() {
        authService.resetPassword();
    }

}
