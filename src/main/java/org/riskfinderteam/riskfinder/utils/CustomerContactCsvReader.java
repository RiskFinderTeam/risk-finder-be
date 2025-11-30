package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerContact;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerContactRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; // 트랜잭션 추가

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
@RequiredArgsConstructor
public class CustomerContactCsvReader {
    private final CustomerContactRepository customerContactRepository;
    private final CsvValueParser csvValueParser;

    // 한 번에 저장할 개수 (너무 크면 네트워크 에러, 너무 작으면 속도 느림. 1000~5000 추천)
    private static final int BATCH_SIZE = 1000;

    @Transactional // 저장 작업의 안정성을 위해 추가 권장
    public void readAndSave(String path){
        try{
            log.info("📂 파일 읽기 시작: {}", path);
            Reader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);

            // 헤더 처리 로직 (기존 동일)
            CSVParser tempParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
            List<String> originalHeaders = tempParser.getHeaderNames();
            List<String> cleanHeaders = originalHeaders.stream()
                    .map(h -> h.replace("\uFEFF", "").trim())
                    .toList();
            tempParser.close(); // 헤더 읽기용 파서는 닫아줍니다.

            // 실제 데이터 읽기용 파서 재생성
            reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVFormat cleanFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(cleanHeaders.toArray(new String[0]))
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build();

            CSVParser parser = new CSVParser(reader, cleanFormat);

            List<CustomerContact> batchList = new ArrayList<>();
            int totalCount = 0;

            for(CSVRecord r : parser){
                CustomerContact contact = CustomerContact.builder()
                        .skIdCurr(csvValueParser.parseInt(r.get("SK_ID_CURR")))
                        .daysRegistration(csvValueParser.parseInt(r.get("DAYS_REGISTRATION")))
                        .daysIdPublish(csvValueParser.parseInt(r.get("DAYS_ID_PUBLISH")))
                        .daysLastPhoneChange(csvValueParser.parseInt(r.get("DAYS_LAST_PHONE_CHANGE")))
                        .flagMobil(csvValueParser.parseInt(r.get("FLAG_MOBIL")))
                        .flagEmpPhone(csvValueParser.parseInt(r.get("FLAG_EMP_PHONE")))
                        .flagWorkPhone(csvValueParser.parseInt(r.get("FLAG_WORK_PHONE")))
                        .flagContMobile(csvValueParser.parseInt(r.get("FLAG_CONT_MOBILE")))
                        .flagPhone(csvValueParser.parseInt(r.get("FLAG_PHONE")))
                        .flagEmail(csvValueParser.parseInt(r.get("FLAG_EMAIL")))
                        .build();

                batchList.add(contact);
                totalCount++;

                // ★ 핵심: 리스트가 꽉 차면 저장하고 비우기
                if(batchList.size() >= BATCH_SIZE) {
                    customerContactRepository.saveAll(batchList);
                    customerContactRepository.flush(); // 즉시 DB 전송
                    batchList.clear(); // 메모리 비우기
                    log.info("🚀 현재 {}건 저장 중... (진행 중)", totalCount);
                }
            }

            // 남은 데이터 저장
            if(!batchList.isEmpty()) {
                customerContactRepository.saveAll(batchList);
                customerContactRepository.flush();
            }

            log.info("✅ CSV 처리 최종 완료: 총 {} 건 저장 성공", totalCount);

        } catch (Exception e) {
            log.error("❌ 처리 중 에러 발생", e);
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}