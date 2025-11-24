package org.riskfinderteam.riskfinder.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataResponse;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerExtSourceDataDto;
import org.riskfinderteam.riskfinder.dashboard.repository.CustomerScoringResultsRepository;
import org.riskfinderteam.riskfinder.dashboard.repository.DashboardCustomerInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

private final CustomerScoringResultsRepository customerScoringResultsRepository;
private final DashboardCustomerInfoRepository dashboardCustomerInfoRepository;

    @Override
    public CustomerDataDto getCustomerData(Integer customerId){
        return customerScoringResultsRepository.findBySkIdCurr(customerId);
    }

    @Override
    public List<CustomerDataDto> getCustomerDataList(){
        return customerScoringResultsRepository.findAllData();
    }

    @Override
    public CustomerAverageDataResponse getCustomerAverageData(){
        CustomerAverageDataDto customerAverageDataDto = customerScoringResultsRepository.getAverageScoringData();
        CustomerExtSourceDataDto customerExtSourceDataDto = dashboardCustomerInfoRepository.getExtSourceAverages();

        return new CustomerAverageDataResponse(
                customerAverageDataDto.getScoreAverage(),
                customerAverageDataDto.getGradeAverageA(),
                customerAverageDataDto.getGradeAverageB(),
                customerAverageDataDto.getGradeAverageC(),
                customerExtSourceDataDto.getExtSource1Average(),
                customerExtSourceDataDto.getExtSource2Average(),
                customerExtSourceDataDto.getExtSource3Average()
        );
    }
}
