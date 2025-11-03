package org.riskfinderteam.riskfinder.loan.service;

import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.loan.dto.LoanDetailDto;
import org.riskfinderteam.riskfinder.loan.repository.LoanRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{
    private final LoanRepository loanRepository;

    @Override
    public LoanDetailDto getLoanDetailById(Long Id){
        return loanRepository.findLoanDetailByCustomerId(Id);
    }
}
