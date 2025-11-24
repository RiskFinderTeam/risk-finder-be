package org.riskfinderteam.riskfinder.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.security.Timestamp;

@AllArgsConstructor
@Getter
@Setter
public class CustomerDataDto {
    private Integer skIdCurr;
    private BigDecimal score;
    private String grade;
    private String top3Features;
    private Timestamp scoredAt;
}
