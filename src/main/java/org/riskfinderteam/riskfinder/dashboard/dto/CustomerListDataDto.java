package org.riskfinderteam.riskfinder.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CustomerListDataDto {
    private Integer skIdCurr;
    private String name;
    private BigDecimal score;
    private String grade;
}
