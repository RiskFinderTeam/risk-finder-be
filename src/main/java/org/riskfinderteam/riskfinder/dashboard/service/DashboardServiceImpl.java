package org.riskfinderteam.riskfinder.dashboard.service;

import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.dashboard.dto.*;
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
        return customerScoringResultsRepository.findCustomerDetailById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("해당 고객 데이터를 찾을 수 없습니다. ID: " + customerId));
    }

    @Override
    public List<CustomerListDataDto> getCustomerDataList(){
        return customerScoringResultsRepository.findAllCustomerListData();
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
