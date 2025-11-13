package org.riskfinderteam.riskfinder.dataset.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "customer_info")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class CustomerInfo {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private int skIdCurr;

    @Column(name = "CODE_GENDER", length = 5)
    private  String codeGender;

    @Column(name = "FLAG_OWN_CAR", length = 1)
    private  String flagOwnCar;

    @Column(name = "FLAG_OWN_REALTY", length = 1)
    private  String flagOwnRealty;

    @Column(name = "OWN_CAR_AGE")
    private int ownCarAge;

    @Column(name = "CNT_CHILDREN")
    private int cntChildren;

    @Column(name = "CNT_FAM_MEMBERS", precision = 10, scale = 2)
    private BigDecimal cntFamMembers;

    @Column(name = "NAME_FAMILY_STATUS", length = 50)
    private String nameFamilyStatus;

    @Column(name = "NAME_EDUCATION_TYPE", length = 50)
    private String nameEducationType;

    @Column(name = "NAME_INCOME_TYPE", length = 50)
    private String nameIncomeType;

    @Column(name = "NAME_HOUSING_TYPE", length = 50)
    private String nameHousingType;

    @Column(name = "NAME_TYPE_SUITE", length = 50)
    private String nameTypeSuite;

    @Column(name = "OCCUPATION_TYPE", length = 50)
    private String occupationType;

    @Column(name = "ORGANIZATION_TYPE", length = 50)
    private String organizationType;

    @Column(name = "AMT_INCOME_TOTAL", precision = 18, scale = 2)
    private BigDecimal amtIncomeTotal;

    @Column(name = "DAYS_BIRTH")
    private int daysBirth;

    @Column(name = "DAYS_EMPLOYED")
    private int daysEmployed;

    @Column(name = "EXT_SOURCE_1")
    private double extSource1;

    @Column(name = "EXT_SOURCE_2")
    private double extSource2;

    @Column(name = "EXT_SOURCE_3")
    private double extSource3;
}
