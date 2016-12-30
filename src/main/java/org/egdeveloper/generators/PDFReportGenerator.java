package org.egdeveloper.generators;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.helpers.EntityInfoGetterHelper;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 03.05.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.generators
 */

// TODO: доделать экспорт в pdf
@Component
public class PDFReportGenerator implements IReportGenerator{

    private Map<String, String> fonts_ = new HashMap<>();

    @Autowired
    private EntityInfoGetterHelper entityInfoGetterHelper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Override
    public void generateMedicalTestReport(MedicalTest test, OutputStream output) throws Exception {

        List<Field> indicators = entityInfoGetterHelper.getAllIndicators(test.getClass());
        Patient patient = test.getPatient();

        Document document = new Document();
        PdfWriter.getInstance(document, output);
        document.open();

//        Font header_font = FontFactory.getFont(context.getRealPath(fonts_.get("TIMES_NEW_ROMAN_BOLD")), "Cp1251", BaseFont.EMBEDDED, 18);
//        Font leading_font = FontFactory.getFont(context.getRealPath(fonts_.get("TIMES_NEW_ROMAN_BOLD")), "Cp1251", BaseFont.EMBEDDED, 14);
//        Font ordinary_font = FontFactory.getFont(context.getRealPath(fonts_.get("TIMES_NEW_ROMAN")), "Cp1251", BaseFont.EMBEDDED, 14);

        Font header_font = null;
        Font leading_font = null;
        Font ordinary_font = null;
        Paragraph header_paragraph = new Paragraph(test.retrieveName(), header_font);
        header_paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(header_paragraph);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable mainInfo_table = new PdfPTable(2);
        //mainInfo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);

        //fullName
        mainInfo_table.addCell(new PdfPCell(new Phrase("ФИО пациента: ", leading_font)));
        mainInfo_table.addCell(new PdfPCell(new Phrase(patient.getFullName(), ordinary_font)));

        //gender
        mainInfo_table.addCell(new PdfPCell(new Phrase("Пол: ", leading_font)));
        mainInfo_table.addCell(new PdfPCell(new Phrase(patient.getGender().gender2String(), ordinary_font)));

        //birthdate
        mainInfo_table.addCell(new PdfPCell(new Phrase("Дата рождения: ", leading_font)));
        mainInfo_table.addCell(new PdfPCell(new Phrase(patient.getBirthdate().toString(dateTimeFormatter), ordinary_font)));

        //place of living
        mainInfo_table.addCell(new PdfPCell(new Phrase("Место проживания: ", leading_font)));
        mainInfo_table.addCell(new PdfPCell(new Phrase(patient.getCountry() + ", "
                + patient.getPostIndex() + ", "
                + patient.getRegion() + ", "
                + patient.getCity() + ", "
                + patient.getAddress(),
                ordinary_font
        )));

        //testDate
        mainInfo_table.addCell(new PdfPCell(new Phrase("Дата проведения обследования: ", leading_font)));
        mainInfo_table.addCell(new PdfPCell(new Phrase(test.getTestDate().toString(dateTimeFormatter), ordinary_font)));
        document.add(mainInfo_table);

        document.close();
    }

    @Override
    public void generateDynamicsReport(Patient patient, String testName, Map<String, Map<LocalDate, Double>> dynamics, OutputStream output) throws Exception {

    }

    @Override
    public String buildName(String prefixName) {
        return prefixName + ".pdf";
    }

}
