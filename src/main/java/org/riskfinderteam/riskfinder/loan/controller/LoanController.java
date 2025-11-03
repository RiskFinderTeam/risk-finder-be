package org.riskfinderteam.riskfinder.loan.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.common.dto.CommonResponseDTO;
import org.riskfinderteam.riskfinder.loan.dto.LoanDetailDto;
import org.riskfinderteam.riskfinder.loan.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "대출", description = "대출 관련 API")
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @GetMapping("/{customerId}")
    public CommonResponseDTO<LoanDetailDto> getLoanDetailById(@PathVariable Long customerId){
        return CommonResponseDTO.success(HttpStatus.OK, "고객의 대출 데이터 조회에 성공했습니다", loanService.getLoanDetailById(customerId));
    }
}
