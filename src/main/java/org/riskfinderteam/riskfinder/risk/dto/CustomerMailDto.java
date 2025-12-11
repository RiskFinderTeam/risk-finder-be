package org.riskfinderteam.riskfinder.risk.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerMailDto {
    private String grade;
    private String top3Features;
}
