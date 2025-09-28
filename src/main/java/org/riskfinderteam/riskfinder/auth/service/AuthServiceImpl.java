package org.riskfinderteam.riskfinder.auth.service;

import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.auth.dto.UserLoginRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserLoginResponseDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupResponseDto;
import org.riskfinderteam.riskfinder.auth.entity.User;
import org.riskfinderteam.riskfinder.auth.enums.Role;
import org.riskfinderteam.riskfinder.auth.jwt.JwtProvider;
import org.riskfinderteam.riskfinder.auth.repository.UserRepository;
import org.riskfinderteam.riskfinder.common.exception.BaseException;
import org.riskfinderteam.riskfinder.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final StringRedisTemplate redisTemplate;

    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration;

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto requestDto){
        String email = requestDto.getEmail().toLowerCase();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtProvider.createAccessToken(user.getId(), user.getRole());
        String refreshToken = jwtProvider.createRefreshToken(user.getId());

        redisTemplate.opsForValue().set(
                "refreshToken:" + user.getId(),
                refreshToken,
                refreshExpiration,
                TimeUnit.MILLISECONDS
        );

        return new UserLoginResponseDto(user.getId(), accessToken, refreshToken);
    }

    @Override
    public UserSignupResponseDto signup(UserSignupRequestDto requestDto){
        String email = requestDto.getEmail().toLowerCase();

        if(userRepository.existsByEmail(email)){
            throw new BaseException(ErrorCode.AUTH_EMAIL_EXISTS);
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.ANALYST)
                .build();

        User savedUser = userRepository.save(user);

        return new UserSignupResponseDto(savedUser.getId());
    }

    @Override
    public boolean existsByEmail(String email){
        if(email == null || email.isBlank()){
            return false;
        }
        return userRepository.existsByEmail(email);
    }
}
