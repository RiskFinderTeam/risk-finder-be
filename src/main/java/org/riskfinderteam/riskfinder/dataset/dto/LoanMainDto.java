package org.riskfinderteam.riskfinder.dataset.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class LoanMainDto {
    private Integer skIdCurr;
    private String nameContractType;
    private BigDecimal amtCredit;
    private BigDecimal amtAnnuity;
    private BigDecimal amtGoodsPrice;
    private String weekdayApprProcessStart;
    private Integer hourApprProcessStart;
    private Integer amtReqCreditBureauHour;
    private Integer amtReqCreditBureauDay;
    private Integer amtReqCreditBureauWeek;
    private Integer amtReqCreditBureauMon;
    private Integer amtReqCreditBureauQrt;
    private Integer amtReqCreditBureauYear;
}
