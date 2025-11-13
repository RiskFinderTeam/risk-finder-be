package org.riskfinderteam.riskfinder.dataset.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CustomerInfoDto {
    private int skIdCurr;
    private  String codeGender;
    private  String flagOwnCar;
    private  String flagOwnRealty;
    private int ownCarAge;
    private int cntChildren;
    private BigDecimal cntFamMembers;
    private String nameFamilyStatus;
    private String nameEducationType;
    private String nameIncomeType;
    private String nameHousingType;
    private String nameTypeSuite;
    private String occupationType;
    private String organizationType;
    private BigDecimal amtIncomeTotal;
    private int daysBirth;
    private int daysEmployed;
    private double extSource1;
    private double extSource2;
    private double extSource3;
}
