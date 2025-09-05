package com.hai.minh.ecommerce.modules.file;

import com.hai.minh.ecommerce.constant.Constants;
import com.hai.minh.ecommerce.exception.InvalidFileException;
import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.List;

public final class CSVHelper {

    public static void validateCSVFile(final MultipartFile file) {
        if (!FilenameUtils.isExtension(file.getOriginalFilename(), Constants.CSV_EXTENSIONS)) {
            throw new InvalidFileException("Format file csv invalid!");
        }
    }

    public static <T> List<T> parseCsv(final MultipartFile file, final Class<T> clazz) {
        try {
            validateCSVFile(file);
            final Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            return new CsvToBeanBuilder<T>(reader)
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new InvalidFileException("Fail to parse CSV file {} ", e.getMessage());
        }
    }

    public static <T> byte[] writeCsv(final List<T> data) {
        if (CollectionUtils.isEmpty(data)) {
            throw new InvalidFileException("No data to export to CSV");
        }
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream();
             OutputStreamWriter streamWriter = new OutputStreamWriter(stream);
             CSVWriter writer = new CSVWriter(streamWriter)) {
            StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder<T>(writer)
                    .withSeparator(ICSVWriter.DEFAULT_SEPARATOR)
                    .build();
            beanToCsv.write(data);
            streamWriter.flush();
            return stream.toByteArray();
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            throw new InvalidFileException("Fail to write CSV file {}", e.getMessage());
        }
    }

}
