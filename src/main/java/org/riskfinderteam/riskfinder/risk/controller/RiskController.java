package org.riskfinderteam.riskfinder.risk.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "리스크", description = "리스크 관련 API")
@RequestMapping("/api/v1/risk")
public class RiskController {

    @GetMapping("/explain")
}
