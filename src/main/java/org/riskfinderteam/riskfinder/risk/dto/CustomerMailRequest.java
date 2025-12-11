package org.riskfinderteam.riskfinder.risk.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerMailRequest {
    private String email;
    private Long customerId;
}
