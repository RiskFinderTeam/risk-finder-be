package org.riskfinderteam.riskfinder.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.riskfinderteam.riskfinder.auth.dto.*;
import org.riskfinderteam.riskfinder.auth.jwt.JwtAuthenticationFilter;
import org.riskfinderteam.riskfinder.auth.security.CustomUserDetails;
import org.riskfinderteam.riskfinder.auth.service.AuthService;
import org.riskfinderteam.riskfinder.common.dto.CommonResponseDTO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@Slf4j
@Tag(name = "인증", description = "로그인, 회원가입,토큰 발급 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final StringRedisTemplate redisTemplate;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Operation(summary = "로그인 API", description = "이메일과 비밀번호로 로그인합니다.")
    @PostMapping("/login")
    public CommonResponseDTO<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto, HttpServletResponse response){
        UserLoginResponseDto responseDto = authService.login(requestDto);
        log.info("로그인 요청 email={}", requestDto.getEmail());

        String refreshToken = redisTemplate.opsForValue().get("refreshToken:" + responseDto.getUserId());

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return CommonResponseDTO.success(HttpStatus.OK, "로그인에 성공했습니다.", responseDto);
    }

    @Operation(summary = "회원가입 API", description = "이메일과 비밀번호로 회원가입합니다.")
    @PostMapping("/signup")
    public CommonResponseDTO<UserSignupResponseDto> signup(@RequestBody UserSignupRequestDto requestDto){
        UserSignupResponseDto responseDto = authService.signup(requestDto);
        return CommonResponseDTO.success(HttpStatus.CREATED, "회원가입에 성공했습니다.", responseDto);
    }

    @Operation(summary = "이메일 중복 체크 API", description = "이메일 중복 체크를 진행합니다.")
    @GetMapping("/check-email")
    public CommonResponseDTO<Boolean> checkEmail(@RequestParam String email){
        boolean isAvailable = authService.existsByEmail(email);
        return CommonResponseDTO.success(HttpStatus.OK, "이메일 중복 확인을 완료했습니다.", isAvailable);
    }

    @Operation(summary = "로그아웃 API", description = "로그아웃을 진행합니다.")
    @DeleteMapping("/logout")
    public CommonResponseDTO<Void> logout(@AuthenticationPrincipal CustomUserDetails userDetails){
        authService.logout(userDetails.getUserId());
        return CommonResponseDTO.success(HttpStatus.OK, "로그아웃에 성공했습니다.");
    }

    @Operation(summary = "토큰 재발급 API", description = "토큰을 재발급합니다.")
    @PostMapping("/refresh")
    public CommonResponseDTO<UserLoginResponseDto> refresh(HttpServletRequest request,
                                           HttpServletResponse response){
        UserLoginResponseDto responseDto = authService.refreshToken(request, response);

        String refreshToken = redisTemplate.opsForValue().get("refreshToken:" + responseDto.getUserId());

        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(Duration.ofDays(7))
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return CommonResponseDTO.success(HttpStatus.OK, "토큰 재발급에 성공했습니다.", responseDto);
    }
    
    @Operation(summary = "인증 메일 발송", description = "인증 메일을 발송합니다.")
    @PostMapping("/send-mail/{email}")
    public CommonResponseDTO<Void> sendMail(@PathVariable String email){
        authService.sendMail(email);
        return  CommonResponseDTO.success(HttpStatus.OK, "메일 발송에 성공했습니다.");
    }

    @Operation(summary = "메일 인증", description = "메일 코드를 확인합니다.")
    @PostMapping("/verify-code")
    public CommonResponseDTO<Boolean> verifyCode(@RequestBody EmailVerifyDto verifyDto){
        boolean isVerified = authService.verifyEmailCode(verifyDto.getEmail(), verifyDto.getAuthCode());
        if(isVerified){
            return CommonResponseDTO.success(HttpStatus.OK, "이메일 인증이 완료되었습니다.", true);
        } else{
            return CommonResponseDTO.error("이메일 인증 실패", 401);
        }
    }
}
