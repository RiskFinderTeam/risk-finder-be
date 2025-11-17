package org.riskfinderteam.riskfinder.utils;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CsvValueParser {
    public Integer parseInt(String v) {
        return (v == null || v.isEmpty()) ? null : Integer.parseInt(v);
    }

    public BigDecimal parseDecimal(String v) {
        return (v == null || v.isEmpty()) ? null : new BigDecimal(v);
    }

    public Double parseDouble(String v) {
        return (v == null || v.isEmpty()) ? null : Double.valueOf(v);
    }
}
