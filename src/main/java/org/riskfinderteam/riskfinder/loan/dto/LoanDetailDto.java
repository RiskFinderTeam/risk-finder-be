package org.riskfinderteam.riskfinder.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class LoanDetailDto {
    private Integer skIdCurr;
    private String nameContractType;
    private BigDecimal amtCredit;
    private BigDecimal score;
}
