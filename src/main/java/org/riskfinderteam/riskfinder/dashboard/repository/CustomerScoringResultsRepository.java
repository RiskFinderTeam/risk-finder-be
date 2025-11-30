package org.riskfinderteam.riskfinder.dashboard.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerListDataDto;
import org.riskfinderteam.riskfinder.dashboard.entity.CustomerScoringResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerScoringResultsRepository extends JpaRepository<CustomerScoringResults, Long>{
    @Query("SELECT new org.riskfinderteam.riskfinder.dashboard.dto.CustomerListDataDto(" +
            "c.skIdCurr, c.name, s.score, s.grade) " +
            "FROM Customer c " +
            "JOIN CustomerScoringResults s ON c.skIdCurr = s.skIdCurr")
    List<CustomerListDataDto> findAllCustomerListData();

    @Query("SELECT new org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto(" +
            "c.skIdCurr, c.name, c.phone, c.email, " +
            "s.score, s.grade, s.top3Features) " +
            "FROM Customer c " +
            "JOIN CustomerScoringResults s ON c.skIdCurr = s.skIdCurr " +
            "WHERE c.skIdCurr = :skIdCurr")
    Optional<CustomerDataDto> findCustomerDetailById(@Param("skIdCurr") Integer skIdCurr);

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
