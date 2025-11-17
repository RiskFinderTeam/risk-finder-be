package org.riskfinderteam.riskfinder.dataset.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "customer_contact")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class CustomerContact {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Integer skIdCurr;

    @Column(name = "DAYS_REGISTRATION")
    private Integer daysRegistration;

    @Column(name = "DAYS_ID_PUBLISH")
    private Integer daysIdPublish;

    @Column(name = "DAYS_LAST_PHONE_CHANGE")
    private Integer daysLastPhoneChange;

    @Column(name = "FLAG_MOBIL")
    private Integer flagMobil;

    @Column(name = "FLAG_EMP_PHONE")
    private Integer flagEmpPhone;

    @Column(name = "FLAG_WORK_PHONE")
    private Integer flagWorkPhone;

    @Column(name = "FLAG_CONT_MOBILE")
    private Integer flagContMobile;

    @Column(name = "FLAG_PHONE")
    private Integer flagPhone;

    @Column(name = "FLAG_EMAIL")
    private Integer flagEmail;
}
