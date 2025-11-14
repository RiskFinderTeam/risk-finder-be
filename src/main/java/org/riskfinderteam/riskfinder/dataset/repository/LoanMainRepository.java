package org.riskfinderteam.riskfinder.dataset.repository;

import org.riskfinderteam.riskfinder.dataset.dto.LoanMainDto;
import org.riskfinderteam.riskfinder.dataset.entity.LoanMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanMainRepository extends JpaRepository<LoanMain, Long> {
}
