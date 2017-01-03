package org.egdeveloper.generators.impl;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
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
import java.util.List;
import java.util.Map;

/**
 * Developer: Roman Yarnykh (egdeveloper@mail.ru)
 * Creation-Date: 03.05.2016
 * Project: LaksmiMed
 * Package: org.egdeveloper.generators
 */

@Component
public class JSONReportGenerator implements IReportGenerator {

    @Autowired
    private EntityInfoGetterHelper entityInfoGetterHelper;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd.MM.yyyy");

    @Override
    public void generateMedicalTestReport(MedicalTest test, OutputStream output) throws Exception {
        List<Field> indicators = entityInfoGetterHelper.getAllIndicators(test.getClass());
        Patient patient = test.getPatient();
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator jg = jsonFactory.createGenerator(output, JsonEncoding.UTF8);
        jg.writeStartObject();
        jg.writeFieldName("patient");
        jg.writeStartObject();
        jg.writeStringField("fullName", patient.getFullName());
        jg.writeStringField("gender", patient.getGender().gender2String());
        jg.writeStringField("birthdate", patient.getBirthdate().toString(dateTimeFormatter));
        jg.writeStringField("address", patient.getCountry() + ", "
                + patient.getPostIndex() + ", "
                + patient.getRegion() + ", "
                + patient.getCity() + ", "
                + patient.getAddress());
        jg.writeEndObject(); //patient
        jg.writeFieldName("test");
        jg.writeStartObject();
        jg.writeStringField("testDate", test.getTestDate().toString(dateTimeFormatter));
        jg.writeFieldName("indicators");
        jg.writeStartObject();
        for(Field indicator : indicators){
            Object indicator_value = indicator.get(test);
            if(indicator_value == null)
                jg.writeNullField(indicator.getName());
            else if(indicator.getType().isAssignableFrom(Double.class))
                jg.writeNumberField(indicator.getName(), (Double)indicator_value);
            else if(indicator.getType().isAssignableFrom(Integer.class))
                jg.writeNumberField(indicator.getName(), (Integer)indicator_value);
            else
                jg.writeStringField(indicator.getName(), indicator_value.toString());
        }
        jg.writeEndObject(); //indicators
        jg.writeEndObject(); //test
        jg.writeEndObject(); //report
        jg.close();
    }

    @Override
    public void generateDynamicsReport(Patient patient, String testName, Map<String, Map<LocalDate, Double>> dynamics, OutputStream output) throws Exception {

    }

    @Override
    public String buildName(String prefixName) {
        return prefixName + ".json";
    }
}
