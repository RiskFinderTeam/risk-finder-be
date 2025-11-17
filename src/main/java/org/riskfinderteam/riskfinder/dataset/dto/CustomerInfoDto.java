package org.riskfinderteam.riskfinder.dataset.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CustomerInfoDto {
    private Integer skIdCurr;
    private  String codeGender;
    private  String flagOwnCar;
    private  String flagOwnRealty;
    private Integer ownCarAge;
    private Integer cntChildren;
    private BigDecimal cntFamMembers;
    private String nameFamilyStatus;
    private String nameEducationType;
    private String nameIncomeType;
    private String nameHousingType;
    private String nameTypeSuite;
    private String occupationType;
    private String organizationType;
    private BigDecimal amtIncomeTotal;
    private Integer daysBirth;
    private Integer daysEmployed;
    private Double extSource1;
    private Double extSource2;
    private Double extSource3;
}
