package org.riskfinderteam.riskfinder.risk.service;

import org.riskfinderteam.riskfinder.risk.dto.CustomerFactorDto;
import org.riskfinderteam.riskfinder.risk.dto.CustomerMailDto;

public interface RiskService {
    CustomerFactorDto getTop3FeaturesById(Long id);
    void sendRiskAlertMail(String toEmail, Long customerId);
}
