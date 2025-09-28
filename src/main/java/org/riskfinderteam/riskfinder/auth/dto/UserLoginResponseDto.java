package org.riskfinderteam.riskfinder.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
public class UserLoginResponseDto {
    private String token;
    private Long userId;
}
