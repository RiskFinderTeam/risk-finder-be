package org.riskfinderteam.riskfinder.loan.service;

import org.riskfinderteam.riskfinder.loan.dto.LoanDetailDto;

public interface LoanService {
    LoanDetailDto getLoanDetailById(Long id);
}
