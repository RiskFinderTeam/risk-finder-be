package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerInfo;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerInfoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomerInfoCsvReader {

    private final CustomerInfoRepository customerInfoRepository;
    private final CsvValueParser csvValueParser;

    // 한 번에 저장할 데이터 개수
    private static final int BATCH_SIZE = 1000;

    @Transactional
    public void readAndSave(String path){
        try{
            log.info("📂 CustomerInfo 데이터 로딩 시작: {}", path);

            // 1. 헤더 정리를 위한 임시 읽기
            Reader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVParser tempParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
            List<String> originalHeaders = tempParser.getHeaderNames();
            List<String> cleanHeaders = originalHeaders.stream()
                    .map(h -> h.replace("\uFEFF", "").trim())
                    .toList();
            tempParser.close();

            // 2. 실제 데이터 읽기
            reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVFormat cleanFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(cleanHeaders.toArray(new String[0]))
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build();

            CSVParser parser = new CSVParser(reader, cleanFormat);

            List<CustomerInfo> batchList = new ArrayList<>();
            int totalCount = 0;

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

                batchList.add(entity);
                totalCount++;

                // ★ 핵심: 1000개 찰 때마다 DB 전송 및 메모리 초기화
                if (batchList.size() >= BATCH_SIZE) {
                    customerInfoRepository.saveAll(batchList);
                    customerInfoRepository.flush(); // 즉시 전송
                    batchList.clear(); // 비우기
                    log.info("🚀 CustomerInfo 데이터 {}건 저장 중...", totalCount);
                }
            }

            // 남은 데이터 저장
            if (!batchList.isEmpty()) {
                customerInfoRepository.saveAll(batchList);
                customerInfoRepository.flush();
            }

            log.info("✅ CustomerInfo DB 저장 최종 완료: 총 {} rows", totalCount);

        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}