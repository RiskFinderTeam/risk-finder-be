package org.riskfinderteam.riskfinder.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserLoginResponseDto {
    private Long userId;
    private String accessToken;
    private String refreshToken;
}
