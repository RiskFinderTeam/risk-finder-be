package org.riskfinderteam.riskfinder.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.riskfinderteam.riskfinder.auth.dto.UserLoginRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserLoginResponseDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupRequestDto;
import org.riskfinderteam.riskfinder.auth.dto.UserSignupResponseDto;
import org.riskfinderteam.riskfinder.auth.security.CustomUserDetails;
import org.riskfinderteam.riskfinder.auth.service.AuthService;
import org.riskfinderteam.riskfinder.common.dto.CommonResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "인증", description = "로그인, 회원가입,토큰 발급 관련 API")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "로그인 API", description = "이메일과 비밀번호로 로그인합니다.")
    @PostMapping("/login")
    public CommonResponseDTO<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto){
        UserLoginResponseDto responseDto = authService.login(requestDto);
        log.info("로그인 요청 email={}", requestDto.getEmail());

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
}
