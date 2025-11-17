package org.riskfinderteam.riskfinder.loan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "scoring_results")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class LoanScore {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Integer skIdCurr;

    @Column(name = "SCORE", nullable = false, precision = 5, scale = 4)
    private BigDecimal score;
}
