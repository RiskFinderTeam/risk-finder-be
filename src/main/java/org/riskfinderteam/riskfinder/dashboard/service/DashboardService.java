package org.riskfinderteam.riskfinder.dashboard.service;

import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataResponse;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto;

import java.util.List;

public interface DashboardService {
    public CustomerDataDto getCustomerData(Integer customerId);
    public List<CustomerDataDto> getCustomerDataList();
    public CustomerAverageDataResponse getCustomerAverageData();
}
