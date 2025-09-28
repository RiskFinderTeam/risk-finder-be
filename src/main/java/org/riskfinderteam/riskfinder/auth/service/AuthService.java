package org.riskfinderteam.riskfinder.auth.service;


import org.riskfinderteam.riskfinder.auth.dto.UserLoginRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserLoginResponseDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupResponseDto;

public interface AuthService {

    UserLoginResponseDto login(UserLoginRequestDto requestDto);

    UserSignupResponseDto signup(UserSignupRequestDto requestDto);

    boolean existsByEmail(String email);
}