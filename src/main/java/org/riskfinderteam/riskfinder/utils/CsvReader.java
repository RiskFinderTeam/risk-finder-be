package org.riskfinderteam.riskfinder.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<String[]> read(String filePath) {
        List<String[]> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
