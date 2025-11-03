package org.riskfinderteam.riskfinder.loan.repository;

import org.riskfinderteam.riskfinder.loan.dto.LoanDetailDto;
import org.riskfinderteam.riskfinder.loan.entity.LoanMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanMain, Long> {
    @Query("""
        SELECT new org.riskfinderteam.riskfinder.loan.dto.LoanDetailDto(
            l.skIdCurr,
            l.nameContractType,
            l.amtCredit,
            s.score
        )
        FROM LoanMain l
        JOIN LoanScore s ON l.skIdCurr = s.skIdCurr
        WHERE l.skIdCurr = :customerId
    """)
    LoanDetailDto findLoanDetailByCustomerId(@Param("customerId") Long customerId);
}
