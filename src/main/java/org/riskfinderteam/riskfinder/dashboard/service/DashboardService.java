package org.riskfinderteam.riskfinder.dashboard.service;

import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataResponse;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerListDataDto;

import java.util.List;

public interface DashboardService {
    CustomerDataDto getCustomerData(Integer customerId);
    List<CustomerListDataDto> getCustomerDataList();
    CustomerAverageDataResponse getCustomerAverageData();
}
