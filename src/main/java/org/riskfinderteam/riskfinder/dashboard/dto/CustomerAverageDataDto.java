package org.riskfinderteam.riskfinder.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomerAverageDataDto {
    private Double scoreAverage;
    private Double gradeAverageA;
    private Double gradeAverageB;
    private Double gradeAverageC;
    private Double gradeAverageD;
    private Double gradeAverageE;
}
