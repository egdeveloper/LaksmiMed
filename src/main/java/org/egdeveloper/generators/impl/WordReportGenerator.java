package org.egdeveloper.generators.impl;

import org.apache.poi.xwpf.usermodel.*;
import org.egdeveloper.attributes.DisplayName;
import org.egdeveloper.data.model.MedicalTest;
import org.egdeveloper.data.model.Patient;
import org.egdeveloper.generators.IReportGenerator;
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
 * Creation-Date: 29.04.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.generators
 */

@Component
public class WordReportGenerator implements IReportGenerator {

    private Map<String, String> fonts_ = new HashMap<>();

    @Autowired
    private EntityInfoGetterHelper entityInfoGetterHelper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Override
    public void generateMedicalTestReport(MedicalTest test, OutputStream output) throws Exception {

        List<Field> indicators = entityInfoGetterHelper.getAllIndicators(test.getClass());

        Patient patient = test.getPatient();
        XWPFDocument document = new XWPFDocument();

        //header
        XWPFParagraph header_paragraph = document.createParagraph();
        header_paragraph.setSpacingAfter(2);
        header_paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun header_content = header_paragraph.createRun();
        header_content.setBold(true);
        header_content.setFontFamily("Times New Roman");
        header_content.setFontSize(18);
        header_content.setText(test.retrieveName());
        //header: end

        //main information
        XWPFTable mainInfo_table = document.createTable(5, 2);
        mainInfo_table.getRow(0).getCell(0).setText("ФИО пациента:");
        mainInfo_table.getRow(0).getCell(1).setText(patient.getFullName());
        mainInfo_table.getRow(1).getCell(0).setText("Пол:");
        mainInfo_table.getRow(1).getCell(1).setText(patient.getGender().gender2String());
        mainInfo_table.getRow(2).getCell(0).setText("Дата рождения:");
        mainInfo_table.getRow(2).getCell(1).setText(patient.getBirthdate().toString(dateTimeFormatter));
        mainInfo_table.getRow(3).getCell(0).setText("Место проживания:");
        mainInfo_table.getRow(3).getCell(1).setText(patient.getCountry() + ", "
                + patient.getPostIndex() + ", "
                + patient.getRegion() + ", "
                + patient.getCity() + ", "
                + patient.getAddress());
        mainInfo_table.getRow(4).getCell(0).setText("Дата проведения обследования:");
        mainInfo_table.getRow(4).getCell(1).setText(test.getTestDate().toString(dateTimeFormatter));
        //main information: end

        document.createParagraph().setSpacingAfter(2);
        //indicators
        XWPFTable indicators_table = document.createTable(indicators.size() + 1, 2);
        indicators_table.getRow(0).getCell(0).setText("Показатель");
        indicators_table.getRow(0).getCell(1).setText("Значение");
        int row_index = 1;
        for(Field indicator : indicators){
            indicators_table.getRow(row_index).getCell(0).setText(indicator.getAnnotation(DisplayName.class).value());
            Object indicator_value = indicator.get(test);
            if(indicator_value == null)
                indicators_table.getRow(row_index).getCell(1).setText("");
            else
                indicators_table.getRow(row_index).getCell(1).setText(indicator_value.toString());
            row_index++;
        }
        //indicators: end
        document.write(output);
        output.flush();
    }

    @Override
    public void generateDynamicsReport(Patient patient,
                                       String testName,
                                       Map<String, Map<LocalDate, Double>> dynamics,
                                       OutputStream output) throws Exception
    {
        XWPFDocument document = new XWPFDocument();
        Class testClazz = Class.forName("org.egdeveloper.data.model." + testName.substring(0, 1).toUpperCase() + testName.substring(1) + "Test");
        String testDisplayName = ((DisplayName)testClazz.getAnnotation(DisplayName.class)).value();
        //header
        XWPFParagraph header_paragraph = document.createParagraph();
        header_paragraph.setSpacingAfter(2);
        header_paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun header_content = header_paragraph.createRun();
        header_content.setBold(true);
        header_content.setFontFamily("Times New Roman");
        header_content.setFontSize(18);
        header_content.setText(testDisplayName);
        //header: end

        //main information
        XWPFTable mainInfo_table = document.createTable(5, 2);
        mainInfo_table.getRow(0).getCell(0).setText("ФИО пациента:");
        mainInfo_table.getRow(0).getCell(1).setText(patient.getFullName());
        mainInfo_table.getRow(1).getCell(0).setText("Пол:");
        mainInfo_table.getRow(1).getCell(1).setText(patient.getGender().gender2String());
        mainInfo_table.getRow(2).getCell(0).setText("Дата рождения:");
        mainInfo_table.getRow(2).getCell(1).setText(patient.getBirthdate().toString(dateTimeFormatter));
        mainInfo_table.getRow(3).getCell(0).setText("Место проживания:");
        mainInfo_table.getRow(3).getCell(1).setText(patient.getCountry() + ", "
                + patient.getPostIndex() + ", "
                + patient.getRegion() + ", "
                + patient.getCity() + ", "
                + patient.getAddress());
        //main information: end

        for(Map.Entry<String, Map<LocalDate, Double>> indicator : dynamics.entrySet()){

            //space between indicators
            document.createParagraph().setSpacingAfter(2);

            //indicator name
//            XWPFParagraph indicatorParagraph = document.createParagraph();
//            header_paragraph.setAlignment(ParagraphAlignment.CENTER);
//            XWPFRun content = indicatorParagraph.createRun();
//            header_content.setBold(true);
//            header_content.setFontFamily("Times New Roman");
//            header_content.setFontSize(14);
//            header_content.setText(indicator.getKey());
//            header_paragraph.setSpacingAfter(2);

            Map<LocalDate, Double> indDynamics = indicator.getValue();
            XWPFTable indicators_table = document.createTable(indDynamics.size() + 2, 2);
            indicators_table.getRow(0).getCell(0).setText(indicator.getKey());
            indicators_table.getRow(1).getCell(0).setText("Дата проведения");
            indicators_table.getRow(1).getCell(1).setText("Значение");
            int row_index = 2;
            for(Map.Entry<LocalDate, Double> dateValue : indDynamics.entrySet()){
                if(dateValue.getValue() == null)
                    continue;
                indicators_table.getRow(row_index).getCell(0).setText(dateValue.getKey().toString());
                indicators_table.getRow(row_index).getCell(1).setText(dateValue.getValue().toString());
                row_index++;
            }
        }
        document.write(output);
        output.flush();
    }

    @Override
    public String buildName(String prefixName) {
        return prefixName + ".docx";
    }

}
