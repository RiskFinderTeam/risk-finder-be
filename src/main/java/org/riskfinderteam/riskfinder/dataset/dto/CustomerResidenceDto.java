package org.riskfinderteam.riskfinder.dataset.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CustomerResidenceDto {
    private int skIdCurr;
    private BigDecimal regionPopulationRelative;
    private int regionRatingClient;
    private int regionRatingClientWCity;
    private int regionNotLiveRegion;
    private int regionNotWorkRegion;
    private int liveRegionNotWorkRegion;
    private int cityNotLiveCity;
    private int cityNotWorkCity;
    private int liveCityNotWorkCity;
    private BigDecimal apartmentsAvg;
    private BigDecimal apartmentsMode;
    private BigDecimal apartmentsMedi;
    private BigDecimal basementAreaAvg;
    private BigDecimal basementAreaMode;
    private BigDecimal basementAreaMedi;
    private BigDecimal yearsBeginExpluationAvg;
    private BigDecimal yearsBeginExpluationMode;
    private BigDecimal yearsBeginExpluationMedi;
    private BigDecimal yearsBuildAvg;
    private BigDecimal yearsBuildMode;
    private BigDecimal yearsBuildMedi;
    private BigDecimal commonAreaAvg;
    private BigDecimal commonAreaMode;
    private BigDecimal commonAreaMedi;
    private BigDecimal elevatorsAvg;
    private BigDecimal elevatorsMode;
    private BigDecimal elevatorsMedi;
    private BigDecimal entrancesAvg;
    private BigDecimal entrancesMode;
    private BigDecimal entrancesMedi;
    private BigDecimal floorsMaxAvg;
    private BigDecimal floorsMaxMode;
    private BigDecimal floorsMaxMedi;
    private BigDecimal floorsMinAvg;
    private BigDecimal floorsMinMode;
    private BigDecimal floorsMinMedi;
    private BigDecimal landedAreaAvg;
    private BigDecimal landedAreaMode;
    private BigDecimal landedAreaMedi;
    private BigDecimal livingAppointmentsAvg;
    private BigDecimal livingAppointmentsMode;
    private BigDecimal livingAppointmentsMedi;
    private BigDecimal livingAreaAvg;
    private BigDecimal livingAreaMode;
    private BigDecimal livingAreaMedi;
    private BigDecimal nonLivingAppointmentsAvg;
    private BigDecimal nonLivingAppointmentsMode;
    private BigDecimal nonLivingAppointmentsMedi;
    private BigDecimal nonLivingAreaAvg;
    private BigDecimal nonLivingAreaMode;
    private BigDecimal nonLivingAreaMedi;
    private String fontDKapremont;
    private String houseTypeMode;
    private BigDecimal totalAreaModel;
    private String wallsMaterialModel;
    private String emergencyStateModel;
}
