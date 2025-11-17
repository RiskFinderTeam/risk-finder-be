package org.riskfinderteam.riskfinder.dataset.service;

import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.utils.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DatasetServiceImpl implements DatasetService {
    private final CustomerContactCsvReader customerContactCsvReader;
    private final CustomerDocsCsvReader customerDocsCsvReader;
    private final CustomerInfoCsvReader customerInfoCsvReader;
    private final CustomerResidenceCsvReader customerResidenceCsvReader;
    private final LoanMainCsvReader loanMainCsvReader;

    @Value("${path.loan-main}")
    private String loanMainPath;

    @Value("${path.customer-contact}")
    private String customerContactPath;

    @Value("${path.customer-docs}")
    private String customerDocsPath;

    @Value("${path.customer-info}")
    private String customerInfoPath;

    @Value("${path.customer-residence}")
    private String customerResidencePath;

    @Override
    public void saveData(){
        customerContactCsvReader.readAndSave(customerContactPath);
        customerDocsCsvReader.readAndSave(customerDocsPath);
        customerInfoCsvReader.readAndSave(customerInfoPath);
        customerResidenceCsvReader.readAndSave(customerResidencePath);
        loanMainCsvReader.readAndSave(loanMainPath);
    }
}
