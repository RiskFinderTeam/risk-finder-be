package org.riskfinderteam.riskfinder.dataset.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.riskfinderteam.riskfinder.common.dto.CommonResponseDTO;
import org.riskfinderteam.riskfinder.dataset.service.DatasetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "데이터 저장", description = "데이터 저장 API")
@RequestMapping("/api/v1/dataset")
@RequiredArgsConstructor
public class DatasetController {
    private final DatasetService datasetService;

    @PostMapping("/save")
    public CommonResponseDTO<Void> saveData(){
        datasetService.saveData();
        return CommonResponseDTO.success(HttpStatus.OK, "데이터 저장을 완료했습니다.", null);
    }
}
