package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerResidence;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerResidenceRepository;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomerResidenceCsvReader {

    private final CustomerResidenceRepository customerResidenceRepository;

    public void readAndSave(String path) {
        try {
            Reader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());

            List<String> originalHeaders = parser.getHeaderNames();
            List<String> cleanHeaders = originalHeaders.stream()
                    .map(h -> h.replace("\uFEFF", "").trim())
                    .toList();
            log.info("정상화된 헤더: {}", cleanHeaders);

            CSVFormat cleanFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(cleanHeaders.toArray(new String[0]))
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build();

            parser = new CSVParser(reader, cleanFormat);

            List<CustomerResidence> list = new ArrayList<>();

            for (CSVRecord r : parser) {
                CustomerResidence entity = CustomerResidence.builder()
                        .skIdCurr(parseInt(r, "SK_ID_CURR"))
                        .regionPopulationRelative(parseDecimal(r, "REGION_POPULATION_RELATIVE"))
                        .regionRatingClient(parseInt(r, "REGION_RATING_CLIENT"))
                        .regionRatingClientWCity(parseInt(r, "REGION_RATING_CLIENT_W_CITY"))
                        .regionNotLiveRegion(parseInt(r, "REG_REGION_NOT_LIVE_REGION"))
                        .regionNotWorkRegion(parseInt(r, "REG_REGION_NOT_WORK_REGION"))
                        .liveRegionNotWorkRegion(parseInt(r, "LIVE_REGION_NOT_WORK_REGION"))
                        .cityNotLiveCity(parseInt(r, "REG_CITY_NOT_LIVE_CITY"))
                        .cityNotWorkCity(parseInt(r, "REG_CITY_NOT_WORK_CITY"))
                        .liveCityNotWorkCity(parseInt(r, "LIVE_CITY_NOT_WORK_CITY"))
                        .apartmentsAvg(parseDecimal(r, "APARTMENTS_AVG"))
                        .apartmentsMode(parseDecimal(r, "APARTMENTS_MODE"))
                        .apartmentsMedi(parseDecimal(r, "APARTMENTS_MEDI"))
                        .basementAreaAvg(parseDecimal(r, "BASEMENTAREA_AVG"))
                        .basementAreaMode(parseDecimal(r, "BASEMENTAREA_MODE"))
                        .basementAreaMedi(parseDecimal(r, "BASEMENTAREA_MEDI"))
                        .yearsBeginExpluationAvg(parseDecimal(r, "YEARS_BEGINEXPLUATATION_AVG"))
                        .yearsBeginExpluationMode(parseDecimal(r, "YEARS_BEGINEXPLUATATION_MODE"))
                        .yearsBeginExpluationMedi(parseDecimal(r, "YEARS_BEGINEXPLUATATION_MEDI"))
                        .yearsBuildAvg(parseDecimal(r, "YEARS_BUILD_AVG"))
                        .yearsBuildMode(parseDecimal(r, "YEARS_BUILD_MODE"))
                        .yearsBuildMedi(parseDecimal(r, "YEARS_BUILD_MEDI"))
                        .commonAreaAvg(parseDecimal(r, "COMMONAREA_AVG"))
                        .commonAreaMode(parseDecimal(r, "COMMONAREA_MODE"))
                        .commonAreaMedi(parseDecimal(r, "COMMONAREA_MEDI"))
                        .elevatorsAvg(parseDecimal(r, "ELEVATORS_AVG"))
                        .elevatorsMode(parseDecimal(r, "ELEVATORS_MODE"))
                        .elevatorsMedi(parseDecimal(r, "ELEVATORS_MEDI"))
                        .entrancesAvg(parseDecimal(r, "ENTRANCES_AVG"))
                        .entrancesMode(parseDecimal(r, "ENTRANCES_MODE"))
                        .entrancesMedi(parseDecimal(r, "ENTRANCES_MEDI"))
                        .floorsMaxAvg(parseDecimal(r, "FLOORSMAX_AVG"))
                        .floorsMaxMode(parseDecimal(r, "FLOORSMAX_MODE"))
                        .floorsMaxMedi(parseDecimal(r, "FLOORSMAX_MEDI"))
                        .floorsMinAvg(parseDecimal(r, "FLOORSMIN_AVG"))
                        .floorsMinMode(parseDecimal(r, "FLOORSMIN_MODE"))
                        .floorsMinMedi(parseDecimal(r, "FLOORSMIN_MEDI"))
                        .landedAreaAvg(parseDecimal(r, "LANDAREA_AVG"))
                        .landedAreaMode(parseDecimal(r, "LANDAREA_MODE"))
                        .landedAreaMedi(parseDecimal(r, "LANDAREA_MEDI"))
                        .livingAppointmentsAvg(parseDecimal(r, "LIVINGAPARTMENTS_AVG"))
                        .livingAppointmentsMode(parseDecimal(r, "LIVINGAPARTMENTS_MODE"))
                        .livingAppointmentsMedi(parseDecimal(r, "LIVINGAPARTMENTS_MEDI"))
                        .livingAreaAvg(parseDecimal(r, "LIVINGAREA_AVG"))
                        .livingAreaMode(parseDecimal(r, "LIVINGAREA_MODE"))
                        .livingAreaMedi(parseDecimal(r, "LIVINGAREA_MEDI"))
                        .nonLivingAreaAvg(parseDecimal(r, "NONLIVINGAPARTMENTS_AVG"))
                        .nonLivingAppointmentsMode(parseDecimal(r, "NONLIVINGAPARTMENTS_MODE"))
                        .nonLivingAppointmentsMedi(parseDecimal(r, "NONLIVINGAPARTMENTS_MEDI"))
                        .nonLivingAreaAvg(parseDecimal(r, "NONLIVINGAREA_AVG"))
                        .nonLivingAreaMode(parseDecimal(r, "NONLIVINGAREA_MODE"))
                        .nonLivingAreaMedi(parseDecimal(r, "NONLIVINGAREA_MEDI"))
                        .fontDKapremont(r.get("FONDKAPREMONT_MODE"))
                        .houseTypeMode(r.get("HOUSETYPE_MODE"))
                        .totalAreaModel(parseDecimal(r, "TOTALAREA_MODE"))
                        .wallsMaterialModel(r.get("WALLSMATERIAL_MODE"))
                        .emergencyStateModel(r.get("EMERGENCYSTATE_MODE"))
                        .build();

                list.add(entity);
            }

            log.info("CSV 파싱 완료: 총 {} 개 레코드", list.size());

            customerResidenceRepository.saveAll(list);
            log.info("DB 저장 완료: {} rows", list.size());

        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }

    private Integer parseInt(CSVRecord r, String column) {
        String v = r.get(column);
        return (v == null || v.isEmpty()) ? null : Integer.parseInt(v);
    }

    private BigDecimal parseDecimal(CSVRecord r, String column) {
        String v = r.get(column);
        return (v == null || v.isEmpty()) ? null : new BigDecimal(v);
    }
}
