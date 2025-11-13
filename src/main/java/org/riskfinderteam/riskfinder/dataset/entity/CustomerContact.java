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
    private int skIdCurr;

    @Column(name = "DAYS_REGISTRATION")
    private int daysRegistration;

    @Column(name = "DAYS_ID_PUBLISH")
    private int daysIdPublish;

    @Column(name = "DAYS_LAST_PHONE_CHANGE")
    private int daysLastPhoneChange;

    @Column(name = "FLAG_MOBIL")
    private int flagMobil;

    @Column(name = "FLAG_EMP_PHONE")
    private int flagEmpPhone;

    @Column(name = "FLAG_WORK_PHONE")
    private int flagWorkPhone;

    @Column(name = "FLAG_CONT_MOBILE")
    private int flagContMobile;

    @Column(name = "FLAG_PHONE")
    private int flagPhone;

    @Column(name = "FLAG_EMAIL")
    private int flagEmail;
}
