package org.riskfinderteam.riskfinder.risk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "scoring_results")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class ScoringResult {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Integer skIdCurr;

    @Column(name = "SCORE", nullable = false, precision = 5, scale = 4)
    private BigDecimal score;

    @Column(name = "GRADE", nullable = false)
    String grade;

    @Column(name = "TOP3_FEATURES")
    private String top3Features;

    @Column(name = "SCORED_AT", nullable = false, updatable = false, insertable = false)
    private LocalDateTime scoredAt;
}
