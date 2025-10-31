package org.riskfinderteam.riskfinder.risk.service;

import org.riskfinderteam.riskfinder.risk.dto.CustomerFactorDto;

public interface RiskService {
    CustomerFactorDto getTop3FeaturesById(Long id);
}
