package org.riskfinderteam.riskfinder.dashboard.controller;

import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.common.dto.CommonResponseDTO;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerAverageDataResponse;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerDataDto;
import org.riskfinderteam.riskfinder.dashboard.dto.CustomerListDataDto;
import org.riskfinderteam.riskfinder.dashboard.service.DashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/customer/{customerId}")
    public CommonResponseDTO<CustomerDataDto> getCustomerData(@PathVariable Integer customerId){
        CustomerDataDto dto = dashboardService.getCustomerData(customerId);
        return CommonResponseDTO.success(HttpStatus.OK, "고객 데이터 조회 성공", dto);
    }

    @GetMapping("/customers")
    public CommonResponseDTO<List<CustomerListDataDto>> getCustomerDataList(){
        List<CustomerListDataDto> dtos = dashboardService.getCustomerDataList();
        return CommonResponseDTO.success(HttpStatus.OK, "고객 데이터 리스트 조회 성공", dtos);
    }

    @GetMapping("/statistics")
    public CommonResponseDTO<CustomerAverageDataResponse> getCustomerAverageData(){
        CustomerAverageDataResponse dto = dashboardService.getCustomerAverageData();
        return CommonResponseDTO.success(HttpStatus.OK, "고객 평균 데이터 조회 성공", dto);
    }
}
