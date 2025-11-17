package org.riskfinderteam.riskfinder.dataset.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "DatasetLoanMain")
@Table(name = "loan_main")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class LoanMain {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Integer skIdCurr;

    @Column(name = "NAME_CONTRACT_TYPE", length = 50)
    private String nameContractType;

    @Column(name = "AMT_CREDIT", precision = 18, scale = 2)
    private BigDecimal amtCredit;

    @Column(name = "AMT_ANNUITY", precision = 18, scale = 2)
    private BigDecimal amtAnnuity;

    @Column(name = "AMT_GOODS_PRICE", precision = 18, scale = 2)
    private BigDecimal amtGoodsPrice;

    @Column(name = "WEEKDAY_APPR_PROCESS_START", length = 20)
    private String weekdayApprProcessStart;

    @Column(name = "HOUR_APPR_PROCESS_START")
    private Integer hourApprProcessStart;

    @Column(name = "AMT_REQ_CREDIT_BUREAU_HOUR")
    private Integer amtReqCreditBureauHour;

    @Column(name = "AMT_REQ_CREDIT_BUREAU_DAY")
    private Integer amtReqCreditBureauDay;

    @Column(name = "AMT_REQ_CREDIT_BUREAU_WEEK")
    private Integer amtReqCreditBureauWeek;

    @Column(name = "AMT_REQ_CREDIT_BUREAU_MON")
    private Integer amtReqCreditBureauMon;

    @Column(name = "AMT_REQ_CREDIT_BUREAU_QRT")
    private Integer amtReqCreditBureauQrt;

    @Column(name = "AMT_REQ_CREDIT_BUREAU_YEAR")
    private Integer amtReqCreditBureauYear;
}
