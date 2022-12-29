package com.hai.minh.ecommerce.services.impl;

import com.hai.minh.ecommerce.commons.constants.Constants;
import com.hai.minh.ecommerce.services.OfficeService;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.conversion.XlsxLineLayoutOptions;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Override
    public byte[] pdfToExcel(MultipartFile file, boolean isMerged) {
        byte[] results = new byte[0];
        try {
            if (file != null) {
                PdfDocument pdf = new PdfDocument();
                pdf.loadFromStream(file.getInputStream());
                pdf.getConvertOptions().setPdfToXlsxOptions(new XlsxLineLayoutOptions(!isMerged, false, false));
                String fileName = this.buildExcelNameFromPDFFile(file.getOriginalFilename());
                pdf.saveToFile(fileName, FileFormat.XLSX);
                File fileTemp = new File(fileName);
                results = FileUtils.readFileToByteArray(fileTemp);
                fileTemp.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public String buildExcelNameFromPDFFile(String inputName) {
        String preName = StringUtils.isNotEmpty(inputName) ?
                inputName.replace(Constants.PDF_EXTENSION, Constants.BLANK)
                : Constants.EXCEL_DEFAULT_NAME;
        return preName.concat(new SimpleDateFormat(Constants.DATE_FORMAT_YYYYMMDDHHMMSS_EXTENSION)
                .format(new Date())).concat(Constants.EXCEL_EXTENSION);
    }

    @Override
    public String crackImage() {
        String results = null;
        Tesseract tesseract = new Tesseract();
        try {
            tesseract.setDatapath("D:\\Project\\ecommerce\\Tess4J\\tessdata");
            results = tesseract.doOCR(new File("D:\\Project\\ecommerce\\src\\main\\resources\\Capture.PNG"));
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        return results;
    }

}
