package org.riskfinderteam.riskfinder.loan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "loan_main")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class LoanMain {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Long skIdCurr;

    @Column(name = "NAME_CONTRACT_TYPE", nullable = false)
    private String nameContractType;

    @Column(name = "AMT_CREDIT", nullable = false, precision = 18, scale = 2)
    private BigDecimal amtCredit;
}
