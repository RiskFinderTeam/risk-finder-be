package org.riskfinderteam.riskfinder.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class EmailVerifyDto {
    private String email;
    private String authCode;
}
