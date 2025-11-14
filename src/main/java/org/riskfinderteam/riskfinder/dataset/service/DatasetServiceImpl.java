package org.riskfinderteam.riskfinder.dataset.service;

import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerContactRepository;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerDocsRepository;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerInfoRepository;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerResidenceRepository;
import org.riskfinderteam.riskfinder.loan.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {
    private final LoanRepository loanRepository;
    private final CustomerContactRepository customerContactRepository;
    private final CustomerDocsRepository customerDocsRepository;
    private final CustomerInfoRepository customerInfoRepository;
    private final CustomerResidenceRepository customerResidenceRepository;

    @Value("")
    private final String customerContactPath;

    @Override
    public void saveData(){

    }
}
