package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerDocs;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerDocsRepository;
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
public class CustomerDocsCsvReader {
    private final CustomerDocsRepository customerDocsRepository;
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

            List<CustomerDocs> list = new ArrayList<>();

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

                list.add(entity);
            }

            log.info("CSV 파싱 완료: 총 {} 개 레코드", list.size());

            customerDocsRepository.saveAll(list);
            log.info("DB 저장 완료: {} rows", list.size());

        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}
