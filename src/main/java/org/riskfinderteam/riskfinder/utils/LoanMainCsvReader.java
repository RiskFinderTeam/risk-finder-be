package org.riskfinderteam.riskfinder.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.riskfinderteam.riskfinder.dataset.repository.LoanMainRepository;
import org.riskfinderteam.riskfinder.dataset.entity.LoanMain;
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
public class LoanMainCsvReader {

    private final LoanMainRepository loanMainRepository;
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

            List<LoanMain> list = new ArrayList<>();

            for(CSVRecord r : parser){
                LoanMain loanMain = LoanMain.builder()
                        .skIdCurr(csvValueParser.parseInt(r.get("SK_ID_CURR")))
                        .nameContractType(r.get("NAME_CONTRACT_TYPE"))
                        .amtCredit(csvValueParser.parseDecimal(r.get("AMT_CREDIT")))
                        .amtAnnuity(csvValueParser.parseDecimal(r.get("AMT_ANNUITY")))
                        .amtGoodsPrice(csvValueParser.parseDecimal(r.get("AMT_GOODS_PRICE")))
                        .weekdayApprProcessStart(r.get("WEEKDAY_APPR_PROCESS_START"))
                        .hourApprProcessStart(csvValueParser.parseInt(r.get("HOUR_APPR_PROCESS_START")))
                        .amtReqCreditBureauHour(csvValueParser.parseInt(r.get("AMT_REQ_CREDIT_BUREAU_HOUR")))
                        .amtReqCreditBureauDay(csvValueParser.parseInt(r.get("AMT_REQ_CREDIT_BUREAU_DAY")))
                        .amtReqCreditBureauWeek(csvValueParser.parseInt(r.get("AMT_REQ_CREDIT_BUREAU_WEEK")))
                        .amtReqCreditBureauMon(csvValueParser.parseInt(r.get("AMT_REQ_CREDIT_BUREAU_MON")))
                        .amtReqCreditBureauQrt(csvValueParser.parseInt(r.get("AMT_REQ_CREDIT_BUREAU_QRT")))
                        .amtReqCreditBureauYear(csvValueParser.parseInt(r.get("AMT_REQ_CREDIT_BUREAU_YEAR")))
                        .build();

                list.add(loanMain);
            }

            log.info("CSV 파싱 완료: 총 {} 개 레코드", list.size());

            loanMainRepository.saveAll(list);
            log.info("DB 저장 완료: {} rows", list.size());
        } catch (Exception e) {
            throw new RuntimeException("CSV 로딩 실패: " + e.getMessage(), e);
        }
    }
}
