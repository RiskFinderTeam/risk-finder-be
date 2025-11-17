package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerInfo;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerInfoRepository;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomerInfoCsvReader {
    private final CustomerInfoRepository customerInfoRepository;
    private final CsvValueParser csvValueParser;

    public void readAndSave(String path){
        try{
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

            List<CustomerInfo> list = new ArrayList<>();

            for(CSVRecord r : parser){
                CustomerInfo entity = CustomerInfo.builder()
                        .skIdCurr(csvValueParser.parseInt(r.get("SK_ID_CURR")))
                        .codeGender(r.get("CODE_GENDER"))
                        .flagOwnCar(r.get("FLAG_OWN_CAR"))
                        .flagOwnRealty(r.get("FLAG_OWN_REALTY"))
                        .ownCarAge(csvValueParser.parseInt(r.get("OWN_CAR_AGE")))
                        .cntChildren(csvValueParser.parseInt(r.get("CNT_CHILDREN")))
                        .cntFamMembers(csvValueParser.parseDecimal(r.get("CNT_FAM_MEMBERS")))
                        .nameFamilyStatus(r.get("NAME_FAMILY_STATUS"))
                        .nameEducationType(r.get("NAME_EDUCATION_TYPE"))
                        .nameIncomeType(r.get("NAME_INCOME_TYPE"))
                        .nameHousingType(r.get("NAME_HOUSING_TYPE"))
                        .nameTypeSuite(r.get("NAME_TYPE_SUITE"))
                        .occupationType(r.get("OCCUPATION_TYPE"))
                        .organizationType(r.get("ORGANIZATION_TYPE"))
                        .amtIncomeTotal(csvValueParser.parseDecimal(r.get("AMT_INCOME_TOTAL")))
                        .daysBirth(csvValueParser.parseInt(r.get("DAYS_BIRTH")))
                        .daysEmployed(csvValueParser.parseInt(r.get("DAYS_EMPLOYED")))
                        .extSource1(csvValueParser.parseDouble(r.get("EXT_SOURCE_1")))
                        .extSource2(csvValueParser.parseDouble(r.get("EXT_SOURCE_2")))
                        .extSource3(csvValueParser.parseDouble(r.get("EXT_SOURCE_3")))
                        .build();

                list.add(entity);
            }

            log.info("CSV 파싱 완료: 총 {} 개 레코드", list.size());

            customerInfoRepository.saveAll(list);
            log.info("DB 저장 완료: {} rows", list.size());

        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}
