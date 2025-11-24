package org.riskfinderteam.riskfinder.dashboard.repository;

import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto;
import org.riskfinderteam.riskfinder.dashboard.entity.CustomerScoringResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerScoringResultsRepository extends JpaRepository<CustomerScoringResults, Long>{
    CustomerDataDto findBySkIdCurr(Integer skIdCurr);

    @Query("SELECT new org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto(" +
            "c.skIdCurr, c.score, c.grade, c.top3Features, c.scoredAt) " +
            "FROM CustomerScoringResults c")
    List<CustomerDataDto> findAllData();

    @Query("""
        SELECT new org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataDto(
            AVG(c.score),
            AVG(CASE WHEN c.grade = 'A' THEN 1 ELSE 0 END),
            AVG(CASE WHEN c.grade = 'B' THEN 1 ELSE 0 END),
            AVG(CASE WHEN c.grade = 'C' THEN 1 ELSE 0 END)
        )
        FROM CustomerScoringResults c
    """)
    CustomerAverageDataDto getAverageScoringData();
}
