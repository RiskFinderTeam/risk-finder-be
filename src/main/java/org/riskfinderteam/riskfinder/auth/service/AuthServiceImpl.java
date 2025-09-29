package org.riskfinderteam.riskfinder.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
        log.info("로그인 요청 - email: {}", email);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("로그인 실패 - 존재하지 않는 사용자 email: {}", email);
                    return new BaseException(ErrorCode.USER_NOT_FOUND);
                });

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())){
            log.warn("로그인 실패 - 비밀번호 불일치 email: {}", email);
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
        log.debug("redis에 refreshToken 저장 완료 - key: refreshToken:{}, expire: {}ms",
                user.getId(), refreshExpiration);

        log.info("로그인 성공 - userId: {}, role: {}", user.getId(), user.getRole());

        return new UserLoginResponseDto(user.getId(), accessToken, refreshToken);
    }

    @Override
    public UserSignupResponseDto signup(UserSignupRequestDto requestDto){
        String email = requestDto.getEmail().toLowerCase();
        log.info("회원가입 요청 - email: {}", email);

        if(userRepository.existsByEmail(email)){
            log.warn("회원가입 실패 - 이미 존재하는 email: {}", email);
            throw new BaseException(ErrorCode.AUTH_EMAIL_EXISTS);
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.ANALYST)
                .build();

        User savedUser = userRepository.save(user);

        log.info("회원가입 성공 - userId: {}, email: {}", savedUser.getId(), savedUser.getEmail());

        return new UserSignupResponseDto(savedUser.getId());
    }

    @Override
    public boolean existsByEmail(String email){
        if(email == null || email.isBlank()){
            return false;
        }
        boolean exists = userRepository.existsByEmail(email);
        log.debug("이메일 중복 확인 - email: {}, exists: {}", email, exists);
        return exists;
    }

    @Override
    public void logout(Long userId){
        String key = "refreshToken:" + userId;

        Boolean deleted = redisTemplate.delete(key);
        if(Boolean.TRUE.equals(deleted)){
            log.info("로그아웃 성공 - userId: {}", userId);
        }else{
            log.warn("refreshToken 없음 - userId: {}", userId);
        }
    }
}
