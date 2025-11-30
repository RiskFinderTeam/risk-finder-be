package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerData;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerDataRepository;
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
public class CustomerCsvReader {

    private final CustomerDataRepository customerDataRepository;
    private final CsvValueParser csvValueParser;

    // 한 번에 저장할 데이터 개수 (속도 및 메모리 관리용)
    private static final int BATCH_SIZE = 1000;

    @Transactional
    public void readAndSave(String path){
        try{
            log.info("📂 Customer 데이터 로딩 시작: {}", path);

            // 1. 헤더 정리를 위한 임시 읽기
            Reader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVParser tempParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
            List<String> originalHeaders = tempParser.getHeaderNames();
            List<String> cleanHeaders = originalHeaders.stream()
                    .map(h -> h.replace("\uFEFF", "").trim())
                    .toList();
            tempParser.close(); // 닫아주기

            // 2. 실제 데이터 읽기
            reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVFormat cleanFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(cleanHeaders.toArray(new String[0]))
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build();

            CSVParser parser = new CSVParser(reader, cleanFormat);

            List<CustomerData> batchList = new ArrayList<>();
            int totalCount = 0;

            for(CSVRecord r : parser){
                CustomerData customer = CustomerData.builder()
                        .skIdCurr(csvValueParser.parseInt(r.get("SK_ID_CURR")))
                        .name(r.get("name"))
                        .birth(r.get("birth"))
                        .phone(r.get("phone"))
                        .email(r.get("email"))
                        .build();

                batchList.add(customer);
                totalCount++;

                // ★ 핵심: 1000개 찰 때마다 DB에 밀어넣고 메모리 비우기
                if (batchList.size() >= BATCH_SIZE) {
                    customerDataRepository.saveAll(batchList);
                    customerDataRepository.flush(); // 즉시 전송
                    batchList.clear(); // 리스트 비우기
                    log.info("🚀 Customer 데이터 {}건 저장 중...", totalCount);
                }
            }

            // 남은 데이터 저장
            if (!batchList.isEmpty()) {
                customerDataRepository.saveAll(batchList);
                customerDataRepository.flush();
            }

            log.info("✅ Customer DB 저장 최종 완료: 총 {} rows", totalCount);

        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}