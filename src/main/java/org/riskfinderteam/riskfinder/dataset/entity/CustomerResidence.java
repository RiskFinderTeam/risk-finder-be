package org.riskfinderteam.riskfinder.dataset.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "customer_residence")
@AllArgsConstructor
@Getter @Setter
@NoArgsConstructor @Builder
public class CustomerResidence {
    @Id
    @Column(name = "SK_ID_CURR", nullable = false)
    private Integer skIdCurr;

    @Column(name = "REGION_POPULATION_RELATIVE", precision = 10, scale = 6)
    private BigDecimal regionPopulationRelative;

    @Column(name = "REGION_RATING_CLIENT")
    private Integer regionRatingClient;

    @Column(name ="REGION_RATING_CLIENT_W_CITY")
    private Integer regionRatingClientWCity;

    @Column(name = "REG_REGION_NOT_LIVE_REGION")
    private Integer regionNotLiveRegion;

    @Column(name = "REG_REGION_NOT_WORK_REGION")
    private Integer regionNotWorkRegion;

    @Column(name = "LIVE_REGION_NOT_WORK_REGION")
    private Integer liveRegionNotWorkRegion;

    @Column(name = "REG_CITY_NOT_LIVE_CITY")
    private Integer cityNotLiveCity;

    @Column(name = "REG_CITY_NOT_WORK_CITY")
    private Integer cityNotWorkCity;

    @Column(name = "LIVE_CITY_NOT_WORK_CITY")
    private Integer liveCityNotWorkCity;

    @Column(name = "APARTMENTS_AVG", precision = 10, scale = 6)
    private BigDecimal apartmentsAvg;

    @Column(name = "APARTMENTS_MODE", precision = 10, scale = 6)
    private BigDecimal apartmentsMode;

    @Column(name = "APARTMENTS_MEDI", precision = 10, scale = 6)
    private BigDecimal apartmentsMedi;

    @Column(name = "BASEMENTAREA_AVG", precision = 10, scale = 6)
    private BigDecimal basementAreaAvg;

    @Column(name = "BASEMENTAREA_MODE", precision = 10, scale = 6)
    private BigDecimal basementAreaMode;

    @Column(name = "BASEMENTAREA_MEDI", precision = 10, scale = 6)
    private BigDecimal basementAreaMedi;

    @Column(name = "YEARS_BEGINEXPLUATATION_AVG", precision = 10, scale = 6)
    private BigDecimal yearsBeginExpluationAvg;

    @Column(name = "YEARS_BEGINEXPLUATATION_MODE", precision = 10, scale = 6)
    private BigDecimal yearsBeginExpluationMode;

    @Column(name = "YEARS_BEGINEXPLUATATION_MEDI", precision = 10, scale = 6)
    private BigDecimal yearsBeginExpluationMedi;

    @Column(name = "YEARS_BUILD_AVG", precision = 10, scale = 6)
    private BigDecimal yearsBuildAvg;

    @Column(name = "YEARS_BUILD_MODE", precision = 10, scale = 6)
    private BigDecimal yearsBuildMode;

    @Column(name = "YEARS_BUILD_MEDI", precision = 10, scale = 6)
    private BigDecimal yearsBuildMedi;

    @Column(name = "COMMONAREA_AVG", precision = 10, scale = 6)
    private BigDecimal commonAreaAvg;

    @Column(name = "COMMONAREA_MODE", precision = 10, scale = 6)
    private BigDecimal commonAreaMode;

    @Column(name = "COMMONAREA_MEDI", precision = 10, scale = 6)
    private BigDecimal commonAreaMedi;

    @Column(name = "ELEVATORS_AVG", precision = 10, scale = 6)
    private BigDecimal elevatorsAvg;

    @Column(name = "ELEVATORS_MODE", precision = 10, scale = 6)
    private BigDecimal elevatorsMode;

    @Column(name = "ELEVATORS_MEDI", precision = 10, scale = 6)
    private BigDecimal elevatorsMedi;

    @Column(name = "ENTRANCES_AVG", precision = 10, scale = 6)
    private BigDecimal entrancesAvg;

    @Column(name = "ENTRANCES_MODE", precision = 10, scale = 6)
    private BigDecimal entrancesMode;

    @Column(name = "ENTRANCES_MEDI", precision = 10, scale = 6)
    private BigDecimal entrancesMedi;

    @Column(name = "FLOORSMAX_AVG", precision = 10, scale = 6)
    private BigDecimal floorsMaxAvg;

    @Column(name = "FLOORSMAX_MODE", precision = 10, scale = 6)
    private BigDecimal floorsMaxMode;

    @Column(name = "FLOORSMAX_MEDI", precision = 10, scale = 6)
    private BigDecimal floorsMaxMedi;

    @Column(name = "FLOORSMIN_AVG", precision = 10, scale = 6)
    private BigDecimal floorsMinAvg;

    @Column(name = "FLOORSMIN_MODE", precision = 10, scale = 6)
    private BigDecimal floorsMinMode;

    @Column(name = "FLOORSMIN_MEDI", precision = 10, scale = 6)
    private BigDecimal floorsMinMedi;

    @Column(name = "LANDAREA_AVG", precision = 10, scale = 6)
    private BigDecimal landedAreaAvg;

    @Column(name = "LANDAREA_MODE", precision = 10, scale = 6)
    private BigDecimal landedAreaMode;

    @Column(name = "LANDAREA_MEDI", precision = 10, scale = 6)
    private BigDecimal landedAreaMedi;

    @Column(name = "LIVINGAPARTMENTS_AVG", precision = 10, scale = 6)
    private BigDecimal livingAppointmentsAvg;

    @Column(name = "LIVINGAPARTMENTS_MODE", precision = 10, scale = 6)
    private BigDecimal livingAppointmentsMode;

    @Column(name = "LIVINGAPARTMENTS_MEDI", precision = 10, scale = 6)
    private BigDecimal livingAppointmentsMedi;

    @Column(name = "LIVINGAREA_AVG", precision = 10, scale = 6)
    private BigDecimal livingAreaAvg;

    @Column(name = "LIVINGAREA_MODE", precision = 10, scale = 6)
    private BigDecimal livingAreaMode;

    @Column(name = "LIVINGAREA_MEDI", precision = 10, scale = 6)
    private BigDecimal livingAreaMedi;

    @Column(name = "NONLIVINGAPARTMENTS_AVG", precision = 10, scale = 6)
    private BigDecimal nonLivingAppointmentsAvg;

    @Column(name = "NONLIVINGAPARTMENTS_MODE", precision = 10, scale = 6)
    private BigDecimal nonLivingAppointmentsMode;

    @Column(name = "NONLIVINGAPARTMENTS_MEDI", precision = 10, scale = 6)
    private BigDecimal nonLivingAppointmentsMedi;

    @Column(name = "NONLIVINGAREA_AVG", precision = 10, scale = 6)
    private BigDecimal nonLivingAreaAvg;

    @Column(name = "NONLIVINGAREA_MODE", precision = 10, scale = 6)
    private BigDecimal nonLivingAreaMode;

    @Column(name = "NONLIVINGAREA_MEDI", precision = 10, scale = 6)
    private BigDecimal nonLivingAreaMedi;

    @Column(name = "FONDKAPREMONT_MODE", length = 50)
    private String fontDKapremont;

    @Column(name = "HOUSETYPE_MODE", length = 50)
    private String houseTypeMode;

    @Column(name = "TOTALAREA_MODE", precision = 10, scale = 6)
    private BigDecimal totalAreaModel;

    @Column(name = "WALLSMATERIAL_MODE", length = 50)
    private String wallsMaterialModel;

    @Column(name = "EMERGENCYSTATE_MODE", length = 50)
    private String emergencyStateModel;
}
