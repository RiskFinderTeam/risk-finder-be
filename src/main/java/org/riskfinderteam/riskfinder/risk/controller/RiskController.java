package org.riskfinderteam.riskfinder.risk.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.riskfinderteam.riskfinder.common.dto.CommonResponseDTO;
import org.riskfinderteam.riskfinder.risk.dto.CustomerFactorDto;
import org.riskfinderteam.riskfinder.risk.dto.CustomerMailRequest;
import org.riskfinderteam.riskfinder.risk.service.RiskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "리스크", description = "리스크 관련 API")
@RequestMapping("/api/v1/risk")
@RequiredArgsConstructor
public class RiskController {
    private final RiskService riskService;

    @Operation(summary = "고객 위험 요인 조회 API", description = "특정 고객의 상위 3개 위험 요인을 조회합니다.")
    @GetMapping("/explain/{customerId}")
    public CommonResponseDTO<CustomerFactorDto> getTop3Features(@PathVariable Long customerId){
        CustomerFactorDto customerFactorDto = riskService.getTop3FeaturesById(customerId);
        log.info("고객 : {}의 상위 3개 위험 요인을 조회합니다.", customerId);
        return CommonResponseDTO.success(HttpStatus.OK, "고객의 위험 요인 조회에 성공했습니다.", customerFactorDto);
    }

    @Operation(summary = "고객 메일 발송 API", description = "특정 고객에게 대출 상환 관련 리포트를 전송합니다.")
    @PostMapping("/send-mail")
    public CommonResponseDTO<Void> sendMail(@RequestBody CustomerMailRequest customerMailRequest ){
        String mail = customerMailRequest.getEmail();
        Long customerId = customerMailRequest.getCustomerId();
        riskService.sendRiskAlertMail(mail, customerId);
        return CommonResponseDTO    .success(HttpStatus.OK, "고객에게 메일 발송을 성공했습니다.");
    }
}