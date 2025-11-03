package org.riskfinderteam.riskfinder.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class LoanDetailDto {
    private Long skIdCurr;
    private String nameContractType;
    private BigDecimal amtCredit;
    private BigDecimal score;
}
