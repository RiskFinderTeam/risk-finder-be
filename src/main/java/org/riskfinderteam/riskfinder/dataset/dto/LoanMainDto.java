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
    private int skIdCurr;
    private int target;
    private String nameContractType;
    private BigDecimal amtCredit;
    private BigDecimal amtAnnuity;
    private BigDecimal amtGoodsPrice;
    private String weekdayApprProcessStart;
    private int hourApprProcessStart;
    private int amtReqCreditBureauHour;
    private int amtReqCreditBureauDay;
    private int amtReqCreditBureauWeek;
    private int amtReqCreditBureauMon;
    private int amtReqCreditBureauQrt;
    private int amtReqCreditBureauYear;
}
