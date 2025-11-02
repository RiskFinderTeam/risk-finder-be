package org.riskfinderteam.riskfinder.risk.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.riskfinderteam.riskfinder.risk.dto.CustomerFactorDto;
import org.riskfinderteam.riskfinder.risk.repository.RiskRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RiskServiceImpl implements RiskService{
    private final RiskRepository riskRepository;
    @Override
    public CustomerFactorDto getTop3FeaturesById(Long id){
        return riskRepository.getTop3FeaturesById(id);
    }
}
