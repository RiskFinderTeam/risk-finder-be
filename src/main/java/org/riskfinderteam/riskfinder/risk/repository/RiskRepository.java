package org.riskfinderteam.riskfinder.risk.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.riskfinderteam.riskfinder.risk.dto.CustomerFactorDto;
import org.riskfinderteam.riskfinder.risk.dto.CustomerMailDto;
import org.riskfinderteam.riskfinder.risk.entity.ScoringResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<ScoringResult, Long> {
    CustomerFactorDto getTop3FeaturesBySkIdCurr(Long skIdCurr);

    @Query("SELECT new org.riskfinderteam.riskfinder.risk.dto.CustomerMailDto(s.grade, s.top3Features) " +
            "FROM ScoringResult s " +
            "WHERE s.skIdCurr = :customerId")
    CustomerMailDto getCustomerMailData(@Param("customerId") Long customerId);
}
