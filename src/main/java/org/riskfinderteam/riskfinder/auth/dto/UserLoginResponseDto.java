package org.riskfinderteam.riskfinder.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class UserLoginResponseDto {
    private Long userId;
    private String accessToken;
    private String refreshToken;
}
