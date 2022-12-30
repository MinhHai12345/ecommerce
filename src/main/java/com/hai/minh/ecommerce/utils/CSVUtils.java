package com.hai.minh.ecommerce.utils;

import com.hai.minh.ecommerce.commons.constants.Constants;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

public class CSVUtils {
    private static final Logger logger = LoggerFactory.getLogger(CSVUtils.class);

    public static boolean isCSVFormat(final MultipartFile file) {
        return Constants.CSV_TYPE.equals(file.getContentType());
    }

    public static <T> List<T> csvToDTO(final MultipartFile file, Class<T> clazz) {
        try {
            if (!isCSVFormat(file)) {
                return Collections.emptyList();
            }
            final Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            return new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (IOException e) {
            logger.error(" Convert CSV file to DTO fail! ".concat(e.getMessage()));
        }
        return Collections.emptyList();
    }

    public static <T> byte[] beanToCSV(List<T> beans) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
        try (CSVWriter writer = new CSVWriter(streamWriter)) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(ICSVWriter.DEFAULT_SEPARATOR)
                    .build();
            beanToCsv.write(beans);
            streamWriter.flush();
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            logger.error(" Write CSV file from DTOs fail! ".concat(e.getMessage()));
        }
        return stream.toByteArray();
    }

}
