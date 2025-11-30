package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerDocs;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerDocsRepository;
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
public class CustomerDocsCsvReader {

    private final CustomerDocsRepository customerDocsRepository;
    private final CsvValueParser csvValueParser;

    // 한 번에 저장할 데이터 개수
    private static final int BATCH_SIZE = 1000;

    @Transactional
    public void readAndSave(String path){
        try{
            log.info("📂 CustomerDocs 데이터 로딩 시작: {}", path);

            // 1. 헤더 정리를 위한 임시 읽기
            Reader reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVParser tempParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withTrim());
            List<String> originalHeaders = tempParser.getHeaderNames();
            List<String> cleanHeaders = originalHeaders.stream()
                    .map(h -> h.replace("\uFEFF", "").trim())
                    .toList();
            tempParser.close(); // 임시 파서 닫기

            // 2. 실제 데이터 읽기
            reader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8);
            CSVFormat cleanFormat = CSVFormat.DEFAULT.builder()
                    .setHeader(cleanHeaders.toArray(new String[0]))
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build();

            CSVParser parser = new CSVParser(reader, cleanFormat);

            List<CustomerDocs> batchList = new ArrayList<>();
            int totalCount = 0;

            for(CSVRecord r : parser){
                CustomerDocs entity = CustomerDocs.builder()
                        .skIdCurr(csvValueParser.parseInt(r.get("SK_ID_CURR")))
                        .obs30CntSocialCircle(csvValueParser.parseDecimal(r.get("OBS_30_CNT_SOCIAL_CIRCLE")))
                        .def30CntSocialCircle(csvValueParser.parseDecimal(r.get("DEF_30_CNT_SOCIAL_CIRCLE")))
                        .obs60CntSocialCircle(csvValueParser.parseDecimal(r.get("OBS_60_CNT_SOCIAL_CIRCLE")))
                        .def60CntSocialCircle(csvValueParser.parseDecimal(r.get("DEF_60_CNT_SOCIAL_CIRCLE")))
                        .flagDocument2(r.get("FLAG_DOCUMENT_2"))
                        .flagDocument3(r.get("FLAG_DOCUMENT_3"))
                        .flagDocument4(r.get("FLAG_DOCUMENT_4"))
                        .flagDocument5(r.get("FLAG_DOCUMENT_5"))
                        .flagDocument6(r.get("FLAG_DOCUMENT_6"))
                        .flagDocument7(r.get("FLAG_DOCUMENT_7"))
                        .flagDocument8(r.get("FLAG_DOCUMENT_8"))
                        .flagDocument9(r.get("FLAG_DOCUMENT_9"))
                        .flagDocument10(r.get("FLAG_DOCUMENT_10"))
                        .flagDocument11(r.get("FLAG_DOCUMENT_11"))
                        .flagDocument12(r.get("FLAG_DOCUMENT_12"))
                        .flagDocument13(r.get("FLAG_DOCUMENT_13"))
                        .flagDocument14(r.get("FLAG_DOCUMENT_14"))
                        .flagDocument15(r.get("FLAG_DOCUMENT_15"))
                        .flagDocument16(r.get("FLAG_DOCUMENT_16"))
                        .flagDocument17(r.get("FLAG_DOCUMENT_17"))
                        .flagDocument18(r.get("FLAG_DOCUMENT_18"))
                        .flagDocument19(r.get("FLAG_DOCUMENT_19"))
                        .flagDocument20(r.get("FLAG_DOCUMENT_20"))
                        .flagDocument21(r.get("FLAG_DOCUMENT_21"))
                        .build();

                batchList.add(entity);
                totalCount++;

                // ★ 핵심: 1000개마다 저장 및 비우기
                if (batchList.size() >= BATCH_SIZE) {
                    customerDocsRepository.saveAll(batchList);
                    customerDocsRepository.flush(); // 즉시 전송
                    batchList.clear(); // 메모리 비우기
                    log.info("🚀 CustomerDocs 데이터 {}건 저장 중...", totalCount);
                }
            }

            // 남은 데이터 저장
            if (!batchList.isEmpty()) {
                customerDocsRepository.saveAll(batchList);
                customerDocsRepository.flush();
            }

            log.info("✅ CustomerDocs DB 저장 최종 완료: 총 {} rows", totalCount);

        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}