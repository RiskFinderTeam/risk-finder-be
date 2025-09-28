package org.riskfinderteam.riskfinder.auth.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    ANALYST("ROLE_ANALYST", "직원"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
