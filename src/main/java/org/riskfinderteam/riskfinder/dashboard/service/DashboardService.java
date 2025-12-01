package org.riskfinderteam.riskfinder.dashboard.service;

import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataResponse;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerListDataDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DashboardService {
    CustomerDataDto getCustomerData(Integer customerId);
    Page<CustomerListDataDto> getCustomerDataList(int page);
    Page<CustomerListDataDto> getCustomerDataList(int page, String search, List<String> grades);
    CustomerAverageDataResponse getCustomerAverageData();
}
