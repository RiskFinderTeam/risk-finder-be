package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.entity.CustomerContact;
import org.riskfinderteam.riskfinder.dataset.repository.CustomerContactRepository;
import org.springframework.stereotype.Component;

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

            List<CustomerContact> list = new ArrayList<>();

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

                list.add(contact);
            }

            log.info("CSV 파싱 완료: 총 {} 개 레코드", list.size());

            customerContactRepository.saveAll(list);
            log.info("DB 저장 완료: {} rows", list.size());

        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}
