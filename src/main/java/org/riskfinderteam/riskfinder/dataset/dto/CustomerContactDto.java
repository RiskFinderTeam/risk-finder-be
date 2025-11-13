package org.riskfinderteam.riskfinder.dataset.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomerContactDto {
    private int skIdCurr;
    private int daysRegistration;
    private int daysIdPublish;
    private int daysLastPhoneChange;
    private int flagMobil;
    private int flagEmpPhone;
    private int flagWorkPhone;
    private int flagContMobile;
    private int flagPhone;
    private int flagEmail;
}
