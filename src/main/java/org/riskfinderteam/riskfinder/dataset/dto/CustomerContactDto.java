package org.riskfinderteam.riskfinder.dataset.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerContactDto {
    private Integer skIdCurr;
    private Integer daysRegistration;
    private Integer daysIdPublish;
    private Integer daysLastPhoneChange;
    private Integer flagMobil;
    private Integer flagEmpPhone;
    private Integer flagWorkPhone;
    private Integer flagContMobile;
    private Integer flagPhone;
    private Integer flagEmail;
}
