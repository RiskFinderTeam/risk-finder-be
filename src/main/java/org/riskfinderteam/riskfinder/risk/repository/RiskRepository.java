package org.riskfinderteam.riskfinder.risk.repository;

import org.riskfinderteam.riskfinder.risk.dto.CustomerFactorDto;
import org.riskfinderteam.riskfinder.risk.entity.ScoringResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiskRepository extends JpaRepository<ScoringResult, Long> {
    CustomerFactorDto getTop3FeaturesById(Long id);
}
