package org.riskfinderteam.riskfinder.auth.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.riskfinderteam.riskfinder.auth.dto.UserLoginRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserLoginResponseDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupResponseDto;

public interface AuthService {

    UserLoginResponseDto login(UserLoginRequestDto requestDto);

    UserSignupResponseDto signup(UserSignupRequestDto requestDto);

    boolean existsByEmail(String email);

    void logout(Long userId);

    UserLoginResponseDto refreshToken(HttpServletRequest request, HttpServletResponse response);

    void sendMail(String email);

    boolean verifyEmailCode(String email, String code);
}