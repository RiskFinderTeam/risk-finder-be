package org.riskfinderteam.riskfinder.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.riskfinderteam.riskfinder.auth.enums.Role;

@AllArgsConstructor
@Setter
@Getter
public class UserSignupResponseDto {
    private Long id;
}
